package com.developerxy.youralarms.ui

import com.developerxy.youralarms.ui.model.AlarmTime

val mockAlarms = listOf(
    com.developerxy.youralarms.ui.model.Alarm(
        "Education",
        AlarmTime(23, 50),
        "01000001".toInt(2).toByte(),
        60,
        true,
        null
    ),
    com.developerxy.youralarms.ui.model.Alarm(
        "Rise and shine",
        AlarmTime(8, 35),
        "01000101".toInt(2).toByte(),
        50,
        false,
        null
    ),
)