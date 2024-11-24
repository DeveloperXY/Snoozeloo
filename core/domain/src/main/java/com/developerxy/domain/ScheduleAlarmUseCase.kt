package com.developerxy.domain

import com.developerxy.data.AlarmScheduler
import com.developerxy.model.Alarm

class ScheduleAlarmUseCase(private val alarmScheduler: AlarmScheduler) {
    operator fun invoke(alarm: Alarm) {
        alarmScheduler.schedule(alarm)
    }
}