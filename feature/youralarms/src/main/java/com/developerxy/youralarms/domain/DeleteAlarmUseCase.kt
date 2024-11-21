package com.developerxy.youralarms.domain

import com.developerxy.data.repository.AlarmRepository
import com.developerxy.model.Alarm
import kotlinx.coroutines.flow.Flow

class DeleteAlarmUseCase(private val alarmRepository: AlarmRepository) {
    suspend operator fun invoke(alarm: Alarm) = alarmRepository.deleteAlarm(alarm)
}