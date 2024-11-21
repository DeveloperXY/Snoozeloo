package com.developerxy.youralarms.domain

import com.developerxy.data.repository.AlarmRepository
import com.developerxy.model.Alarm

class ToggleAlarmActiveStateUseCase(private val alarmRepository: AlarmRepository) {
    suspend operator fun invoke(alarm: Alarm) {
        alarmRepository.updateAlarm(
            alarm.copy(isActive = !alarm.isActive)
        )
    }
}