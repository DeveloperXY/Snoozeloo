package com.developerxy.model

data class Alarm(
    val id: Int,
    val name: String,
    val time: AlarmTime,
    val selectedDays: Byte,
    val volume: Int,
    val vibrate: Boolean,
    val ringtone: String = "Default"
)

data class AlarmTime(
    val hours: Int,
    val minutes: Int,
)