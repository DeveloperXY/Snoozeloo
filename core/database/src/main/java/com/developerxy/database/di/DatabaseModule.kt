package com.developerxy.database.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.developerxy.database.SnoozelooDatabase
import org.koin.dsl.module
import java.util.concurrent.Executors

val databaseModule = module {
    single { database(get()) }
}

private fun database(applicationContext: Context): SnoozelooDatabase {
    return Room.databaseBuilder(
        applicationContext,
        SnoozelooDatabase::class.java, "snoozeloo-db"
    ).setQueryCallback({ sqlQuery, bindArgs ->
        Log.d("RoomSQL", "SQL Query: $sqlQuery Args: $bindArgs")
    }, Executors.newSingleThreadExecutor()).build()
}