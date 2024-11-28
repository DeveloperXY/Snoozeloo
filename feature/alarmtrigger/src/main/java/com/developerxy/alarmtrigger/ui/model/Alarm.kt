package com.developerxy.alarmtrigger.ui.model

import com.developerxy.model.Alarm as DomainAlarm

data class Alarm(
    val id: Int,
    val name: String,
    val time: AlarmTime,
    val volume: Int,
    val vibrate: Boolean,
    val ringtoneUri: String?,
)

data class AlarmTime(
    val hours: Int,
    val minutes: Int,
)

fun DomainAlarm.asUiModel() = Alarm(
    id = id,
    name = name,
    time = AlarmTime(hours = time.hours, minutes = time.minutes),
    volume = volume,
    vibrate = vibrate,
    ringtoneUri = ringtoneUri,
)