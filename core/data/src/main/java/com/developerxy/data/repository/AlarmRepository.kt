package com.developerxy.data.repository

import com.developerxy.model.Alarm
import kotlinx.coroutines.flow.Flow

interface AlarmRepository {
    fun getAllAlarms(): Flow<List<Alarm>>
    suspend fun addAlarm(alarm: Alarm)
    suspend fun updateAlarm(alarm: Alarm)
}