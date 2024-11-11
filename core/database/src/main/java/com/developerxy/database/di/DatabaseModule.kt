package com.developerxy.database.di

import android.content.Context
import androidx.room.Room
import com.developerxy.database.SnoozelooDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { database(get()) }
}

private fun database(applicationContext: Context): SnoozelooDatabase {
    return Room.databaseBuilder(
        applicationContext,
        SnoozelooDatabase::class.java, "snoozeloo-db"
    ).build()
}