package com.developerxy.snoozeloo.di

import com.developerxy.data.AlarmScheduler
import com.developerxy.snoozeloo.AndroidAlarmScheduler
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::AndroidAlarmScheduler) {
        bind<AlarmScheduler>()
    }
}