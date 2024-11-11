package com.developerxy.youralarms.ui.model

import com.developerxy.model.Alarm as DomainAlarm

data class Alarm(
    val name: String,
    val time: AlarmTime,
    val selectedDays: Byte,
    val volume: Int,
    val vibrate: Boolean,
    val ringtone: String
)

data class AlarmTime(
    val hours: Int,
    val minutes: Int,
)

fun DomainAlarm.asUiModel() = Alarm(
    name = name,
    time = AlarmTime(hours = time.hours, minutes = time.minutes),
    selectedDays = selectedDays,
    volume = volume,
    vibrate = vibrate,
    ringtone = ringtone
)