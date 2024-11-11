package com.developerxy.youralarms.domain

import com.developerxy.data.repository.AlarmRepository
import com.developerxy.model.Alarm
import kotlinx.coroutines.flow.Flow

class FetchYourAlarmsUseCase(
    private val alarmRepository: AlarmRepository
) {
    operator fun invoke(): Flow<List<Alarm>> = alarmRepository.getAllAlarms()
}