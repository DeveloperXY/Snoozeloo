package com.developerxy.database.di

import com.developerxy.database.SnoozelooDatabase
import com.developerxy.database.dao.AlarmDao
import org.koin.dsl.module

val daoModule = module {
    single { alarmDao(get()) }
}

private fun alarmDao(db: SnoozelooDatabase): AlarmDao = db.alarmDao()