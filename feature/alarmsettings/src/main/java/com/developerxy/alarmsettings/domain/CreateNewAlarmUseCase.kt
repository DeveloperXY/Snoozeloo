package com.developerxy.alarmsettings.domain

import com.developerxy.data.repository.AlarmRepository
import com.developerxy.domain.ScheduleAlarmUseCase
import com.developerxy.model.Alarm

class CreateNewAlarmUseCase(
    private val alarmRepository: AlarmRepository,
    private val scheduleAlarm: ScheduleAlarmUseCase
) {
    suspend operator fun invoke(alarm: Alarm) {
        val newAlarmId = alarmRepository.addAlarm(alarm)
        scheduleAlarm(alarm.copy(id = newAlarmId))
    }
}