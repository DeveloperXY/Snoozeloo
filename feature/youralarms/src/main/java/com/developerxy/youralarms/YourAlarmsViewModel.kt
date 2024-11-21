package com.developerxy.youralarms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developerxy.ui.amOrPm
import com.developerxy.ui.formatAsDisplayTime
import com.developerxy.ui.toSelectedDaysList
import com.developerxy.youralarms.domain.DeleteAlarmUseCase
import com.developerxy.youralarms.domain.FetchYourAlarmsUseCase
import com.developerxy.youralarms.domain.ToggleAlarmActiveStateUseCase
import com.developerxy.youralarms.ui.model.Alarm
import com.developerxy.youralarms.ui.model.asDomainModel
import com.developerxy.youralarms.ui.model.asUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime
import com.developerxy.model.Alarm as DomainAlarm

open class YourAlarmsViewModel(
    private val _fetchYourAlarms: FetchYourAlarmsUseCase,
    private val _toggleAlarmActiveState: ToggleAlarmActiveStateUseCase,
    private val _deleteAlarm: DeleteAlarmUseCase,
) : ViewModel() {
    private val _alarms = MutableStateFlow<List<Alarm>>(emptyList())
    val alarms: StateFlow<List<Alarm>> = _alarms.asStateFlow()

    fun loadAlarms() {
        viewModelScope.launch(Dispatchers.IO) {
            _fetchYourAlarms().map {
                it.map { alarm ->
                    val timeUntilNextOccurrence =
                        if (alarm.isActive) alarm.calculateTimeUntilNextOccurrence() else null
                    val timeRequiredForXHoursOfSleep =
                        if (alarm.isActive) alarm.calculateTimeRequiredForXHoursOfSleep() else null
                    alarm.asUiModel(
                        timeUntilNextOccurrence = timeUntilNextOccurrence,
                        timeRequiredForXHoursOfSleep = timeRequiredForXHoursOfSleep
                    )
                }
            }.collect { alarms ->
                withContext(Dispatchers.Main) {
                    _alarms.value = alarms
                }
            }
        }
    }

    fun toggleAlarmActiveState(alarm: Alarm) {
        viewModelScope.launch {
            _toggleAlarmActiveState(alarm.asDomainModel())
        }
    }

    fun deleteAlarm(alarm: Alarm) {
        viewModelScope.launch {
            _deleteAlarm(alarm.asDomainModel())
        }
    }

    private fun DomainAlarm.nextAlarmDateTime(): LocalDateTime {
        val repeatDays = selectedDays.toSelectedDaysList()
            .map { DayOfWeek.of(it + 1) }
        val (alarmHours, alarmMinutes) = time
        val now = LocalDateTime.now()
        val currentDay = now.dayOfWeek
        val currentTime = now.toLocalTime()

        return if (repeatDays.isEmpty()) {
            // Case 1: Non-repeating alarm
            val alarmToday =
                LocalDateTime.of(now.toLocalDate(), LocalTime.of(alarmHours, alarmMinutes))
            if (alarmToday.isAfter(now)) {
                alarmToday // The alarm is later today
            } else {
                alarmToday.plusDays(1) // The alarm is tomorrow
            }
        } else {
            // Case 2: Repeating alarm on specific days
            // Sort and find the next day for the alarm
            val sortedRepeatDays = repeatDays.sorted()
            val nextAlarmDay = sortedRepeatDays.firstOrNull { day ->
                // Check if the alarm is today and in the future, or on a later day
                (day == currentDay && LocalTime.of(alarmHours, alarmMinutes)
                    .isAfter(currentTime)) || day > currentDay
            } ?: sortedRepeatDays.first() // Wrap around to the next week if no match

            // Calculate days until the next alarm
            val daysUntilNextAlarm = if (nextAlarmDay >= currentDay) {
                nextAlarmDay.ordinal - currentDay.ordinal
            } else {
                7 - currentDay.ordinal + nextAlarmDay.ordinal
            }

            // Construct the next alarm's date and time
            now.plusDays(daysUntilNextAlarm.toLong())
                .withHour(alarmHours)
                .withMinute(alarmMinutes)
                .withSecond(0)
                .withNano(0)
        }
    }

    private fun DomainAlarm.isBetween(lowerHours: Int, upperHours: Int): Boolean {
        return time.hours in lowerHours until upperHours
    }

    private fun DomainAlarm.calculateTimeRequiredForXHoursOfSleep(x: Long = 8): String? {
        val now = LocalDateTime.now()
        val nextAlarmDateTime = nextAlarmDateTime()

        val duration = Duration.between(now, nextAlarmDateTime)

        // If the alarm is set at 10:00, we return null -> not ideal
        if (duration.toDays() >= 1 || !this.isBetween(4, 10)) {
            return null
        }

        val recommendedSleepDateTime = nextAlarmDateTime.minusHours(x)
        val recommendedHours = recommendedSleepDateTime.hour
        val recommendedMinutes = recommendedSleepDateTime.minute

        val amOrPm = amOrPm(recommendedHours)
        val formattedRecommendedTime = formatAsDisplayTime(recommendedHours, recommendedMinutes)

        return "Go to bed at $formattedRecommendedTime$amOrPm to get $x hours of sleep"
    }

    private fun DomainAlarm.calculateTimeUntilNextOccurrence(): String {
        val now = LocalDateTime.now()
        val nextAlarmDateTime = nextAlarmDateTime()
        val duration = Duration.between(now, nextAlarmDateTime)

        val totalMinutes = duration.toMinutes() + 1
        val days = totalMinutes / (24 * 60)
        val hours = (totalMinutes % (24 * 60)) / 60
        val minutes = totalMinutes % 60

        return buildString {
            append("Alarm in ")
            if (days > 0) append("${days}d")
            if (hours > 0) append("${hours}h")
            append("${minutes}min")
        }
    }
}