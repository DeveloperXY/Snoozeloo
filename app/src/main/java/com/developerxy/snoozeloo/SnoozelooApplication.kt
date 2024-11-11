package com.developerxy.snoozeloo

import android.app.Application
import com.developerxy.data.di.repositoryModule
import com.developerxy.database.di.daoModule
import com.developerxy.database.di.databaseModule
import com.developerxy.youralarms.di.yourAlarmsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SnoozelooApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SnoozelooApplication)

            val featureModules = arrayOf(yourAlarmsModule)
            val dataModules = arrayOf(databaseModule, daoModule, repositoryModule)

            modules(*dataModules, *featureModules)
        }
    }
}