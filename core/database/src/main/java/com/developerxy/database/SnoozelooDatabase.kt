package com.developerxy.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.developerxy.database.dao.AlarmDao
import com.developerxy.database.entity.AlarmEntity

@Database(entities = [AlarmEntity::class], version = 1)
abstract class SnoozelooDatabase : RoomDatabase() {
    abstract fun alarmDao(): AlarmDao
}