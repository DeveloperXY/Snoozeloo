package com.developerxy.alarmsettings

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developerxy.data.repository.AlarmRepository
import com.developerxy.model.Alarm
import com.developerxy.model.AlarmTime
import com.developerxy.model.RingtoneInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.concurrent.TimeUnit

class AlarmSettingsViewModel(
    private val alarmRepository: AlarmRepository
) : ViewModel() {
    /**
     * This flag indicates whether the totality of alarm info currently set are valid
     * and an alarm could be created or updated based on these info.
     */
    private val _isAlarmInfoValid = MutableStateFlow(false)
    val isAlarmInfoValid = _isAlarmInfoValid.asStateFlow()

    private val _hours = MutableStateFlow<Int?>(null)
    val hours = _hours.asStateFlow()
    private val _minutes = MutableStateFlow<Int?>(null)
    val minutes = _minutes.asStateFlow()
    private val _alarmTimePreviewText = MutableStateFlow<String?>(null)
    val alarmTimePreviewText = _alarmTimePreviewText.asStateFlow()
    private val _alarmName = MutableStateFlow("")
    val alarmName = _alarmName.asStateFlow()
    private val _volume = MutableStateFlow(50)
    val volume = _volume.asStateFlow()
    private val _shouldAlarmVibrate = MutableStateFlow(true)
    val shouldAlarmVibrate = _shouldAlarmVibrate.asStateFlow()
    private val _selectedDays = MutableStateFlow(listOf<Int>())
    val selectedDays = _selectedDays.asStateFlow()

    private lateinit var _selectedRingtone: MutableStateFlow<RingtoneInfo>

    fun setSelectedRingtone(ringtone: RingtoneInfo) {
        if (!this::_selectedRingtone.isInitialized) {
            _selectedRingtone = MutableStateFlow(ringtone)
        } else {
            _selectedRingtone.value = ringtone
        }
    }

    init {
        viewModelScope.launch {
            combine(hours, minutes) { h, m ->
                h to m
            }.collect { (h, m) ->
                updateAlarmTimePreview(h, m)
                _isAlarmInfoValid.value = h != null && m != null
            }
        }
    }

    fun setHours(hours: Int?) {
        _hours.value = hours
    }

    fun setMinutes(minutes: Int?) {
        _minutes.value = minutes
    }

    fun setAlarmName(name: String) {
        _alarmName.value = name
    }

    fun setVolume(volume: Int) {
        _volume.value = volume
    }

    fun toggleShouldAlarmVibrate() {
        _shouldAlarmVibrate.value = !_shouldAlarmVibrate.value
    }

    fun selectDay(day: Int) {
        _selectedDays.value += day
    }

    fun unselectDay(day: Int) {
        _selectedDays.value -= day
    }

    private fun updateAlarmTimePreview(hours: Int?, minutes: Int?) {
        _alarmTimePreviewText.value = if (hours != null && minutes != null) {
            val now = Calendar.getInstance()
            val currentHour = now.get(Calendar.HOUR_OF_DAY)
            val currentMinute = now.get(Calendar.MINUTE)

            val alarmTimeInMinutes = hours * 60 + minutes
            val currentTimeInMinutes = currentHour * 60 + currentMinute

            val differenceInMinutes = if (alarmTimeInMinutes >= currentTimeInMinutes) {
                alarmTimeInMinutes - currentTimeInMinutes
            } else {
                // If the alarm time is for the next day
                (24 * 60) - currentTimeInMinutes + alarmTimeInMinutes
            }

            val diffHours = TimeUnit.MINUTES.toHours(differenceInMinutes.toLong()).toInt()
            val diffMinutes = (differenceInMinutes % 60)

            buildString {
                append("Alarm in ")
                if (diffHours > 0) append("${diffHours}h ")
                if (diffMinutes > 0) append("${diffMinutes}min")
            }.trim()
        } else {
            null
        }
    }

    @Suppress("NAME_SHADOWING")
    fun saveNewAlarm(@MainThread onComplete: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val alarmTime = hours.value?.let { hours ->
                minutes.value?.let { minutes ->
                    AlarmTime(hours, minutes)
                }
            }

            alarmTime?.let { alarmTime ->
                val newAlarm = Alarm(
                    name = alarmName.value,
                    time = alarmTime,
                    selectedDays = selectedDays.value.asByte(),
                    volume = volume.value,
                    vibrate = shouldAlarmVibrate.value,
                    ringtoneUri = _selectedRingtone.value.uri.toString()
                )
                alarmRepository.addAlarm(newAlarm)
                withContext(Dispatchers.Main) {
                    onComplete()
                }
            }
        }
    }

    private fun List<Int>.asByte(): Byte {
        require(size <= 8) { "Byte can only represent up to 8 bits." }
        return fold(0) { acc, bit -> acc or (1 shl bit) }.toByte()
    }
}