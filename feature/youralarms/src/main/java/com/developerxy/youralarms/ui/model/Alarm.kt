package com.developerxy.youralarms.ui.model

import com.developerxy.domain.toSelectedDaysByte
import com.developerxy.model.Alarm as DomainAlarm
import com.developerxy.model.AlarmTime as DomainAlarmTime

data class Alarm(
    val id: Int,
    val name: String,
    val time: AlarmTime,
    val selectedDays: List<Int>,
    val volume: Int,
    val vibrate: Boolean,
    val ringtoneUri: String?,
    val isActive: Boolean,
    val timeUntilNextOccurrence: String?,
    val timeRequiredForXHoursOfSleep: String?,
)

data class AlarmTime(
    val hours: Int,
    val minutes: Int,
)

fun DomainAlarm.asUiModel(
    timeUntilNextOccurrence: String?,
    timeRequiredForXHoursOfSleep: String?
) = Alarm(
    id = id,
    name = name,
    time = AlarmTime(hours = time.hours, minutes = time.minutes),
    selectedDays = selectedDays.toSelectedDaysList(),
    volume = volume,
    vibrate = vibrate,
    ringtoneUri = ringtoneUri,
    isActive = isActive,
    timeUntilNextOccurrence = timeUntilNextOccurrence,
    timeRequiredForXHoursOfSleep = timeRequiredForXHoursOfSleep,
)

fun Alarm.asDomainModel() = DomainAlarm(
    id = id,
    name = name,
    time = DomainAlarmTime(hours = time.hours, minutes = time.minutes),
    selectedDays = selectedDays.toSelectedDaysByte(),
    volume = volume,
    vibrate = vibrate,
    ringtoneUri = ringtoneUri,
    isActive = isActive,
)