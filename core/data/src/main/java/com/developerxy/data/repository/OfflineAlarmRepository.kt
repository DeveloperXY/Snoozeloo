package com.developerxy.data.repository

import com.developerxy.database.dao.AlarmDao
import com.developerxy.database.entity.AlarmEntity
import com.developerxy.database.entity.asDomainModel
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
}