package com.developerxy.snoozeloo

import android.app.Application
import com.developerxy.alarmsettings.di.alarmSettingsModule
import com.developerxy.data.di.repositoryModule
import com.developerxy.database.di.daoModule
import com.developerxy.database.di.databaseModule
import com.developerxy.di.sharedAlarmSettingsModule
import com.developerxy.domain.di.coreDomainModule
import com.developerxy.ringtonesettings.di.ringtonePickerModule
import com.developerxy.snoozeloo.di.appModule
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

            val featureModules = arrayOf(
                yourAlarmsModule,
                alarmSettingsModule,
                ringtonePickerModule,
                sharedAlarmSettingsModule,
            )
            val dataModules = arrayOf(databaseModule, daoModule, repositoryModule)
            val coreModules = arrayOf(coreDomainModule)

            modules(appModule, *dataModules, *featureModules, *coreModules)
        }
    }
}