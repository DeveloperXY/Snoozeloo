package com.developerxy.data.repository

import com.developerxy.model.Alarm
import kotlinx.coroutines.flow.Flow

interface AlarmRepository {
    fun getAllAlarms(): Flow<List<Alarm>>
    suspend fun getAlarmById(id: Int): Alarm
    suspend fun addAlarm(alarm: Alarm): Int
    suspend fun updateAlarm(alarm: Alarm)
    suspend fun deleteAlarm(alarm: Alarm)
}