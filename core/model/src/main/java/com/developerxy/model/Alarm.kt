package com.developerxy.model

data class Alarm(
    val id: Int = 0,
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