package com.developerxy.youralarms.ui

import com.developerxy.youralarms.ui.model.AlarmTime

val mockAlarms = listOf(
    com.developerxy.youralarms.ui.model.Alarm(
        1,
        "Education",
        AlarmTime(23, 50),
        listOf(1, 6),
        60,
        true,
        null,
        true,
        "Alarm in 1d5h3min",
        "Go to bed at 02:00AM to get 8 hours of sleep"
    ),
    com.developerxy.youralarms.ui.model.Alarm(
        2,
        "Rise and shine",
        AlarmTime(8, 35),
        listOf(1, 2, 4),
        50,
        false,
        null,
        false,
        null,
        null
    ),
)