package com.developerxy.model

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

data class Alarm(
    val id: Int = 0,
    val name: String,
    val time: AlarmTime,
    val selectedDays: Byte,
    val volume: Int,
    val vibrate: Boolean,
    val ringtoneUri: String?,
    val isActive: Boolean
) {
    fun nextTriggerDateTime(): LocalDateTime {
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

    fun Byte.toSelectedDaysList(): List<Int> {
        return (0 until 8)
            .filter { (this.toInt() shr it) and 1 == 1 }
    }
}

data class AlarmTime(
    val hours: Int,
    val minutes: Int,
)