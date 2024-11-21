package com.developerxy.youralarms.ui.model

import com.developerxy.model.Alarm as DomainAlarm
import com.developerxy.model.AlarmTime as DomainAlarmTime

data class Alarm(
    val id: Int,
    val name: String,
    val time: AlarmTime,
    val selectedDays: Byte,
    val volume: Int,
    val vibrate: Boolean,
    val ringtoneUri: String?,
    val isActive: Boolean
)

data class AlarmTime(
    val hours: Int,
    val minutes: Int,
)

fun DomainAlarm.asUiModel() = Alarm(
    id = id,
    name = name,
    time = AlarmTime(hours = time.hours, minutes = time.minutes),
    selectedDays = selectedDays,
    volume = volume,
    vibrate = vibrate,
    ringtoneUri = ringtoneUri,
    isActive = isActive,
)

fun Alarm.asDomainModel() = DomainAlarm(
    id = id,
    name = name,
    time = DomainAlarmTime(hours = time.hours, minutes = time.minutes),
    selectedDays = selectedDays,
    volume = volume,
    vibrate = vibrate,
    ringtoneUri = ringtoneUri,
    isActive = isActive,
)