package com.developerxy.alarmtrigger.domain

import com.developerxy.data.repository.AlarmRepository
import com.developerxy.model.Alarm

class GetAlarmDetailsUseCase(private val alarmRepository: AlarmRepository) {
    suspend operator fun invoke(id: Int): Alarm = alarmRepository.getAlarmById(id)
}