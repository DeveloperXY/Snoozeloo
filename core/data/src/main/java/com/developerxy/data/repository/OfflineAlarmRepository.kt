package com.developerxy.data.repository

import com.developerxy.database.dao.AlarmDao
import com.developerxy.database.entity.AlarmEntity
import com.developerxy.database.entity.asDomainModel
import com.developerxy.database.entity.toDatabaseEntity
import com.developerxy.model.Alarm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class OfflineAlarmRepository(
    private val alarmDao: AlarmDao
) : AlarmRepository {
    override fun getAllAlarms(): Flow<List<Alarm>> = alarmDao.getAll()
        .map {
            it.map(AlarmEntity::asDomainModel)
        }

    override suspend fun addAlarm(alarm: Alarm) {
        alarmDao.insertAll(alarm.toDatabaseEntity())
    }

    override suspend fun updateAlarm(alarm: Alarm) {
        alarmDao.update(alarm.toDatabaseEntity())
    }

    override suspend fun deleteAlarm(alarm: Alarm) {
        alarmDao.delete(alarm.toDatabaseEntity())
    }
}