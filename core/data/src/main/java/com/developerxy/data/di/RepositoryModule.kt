package com.developerxy.data.di

import com.developerxy.data.repository.AlarmRepository
import com.developerxy.data.repository.OfflineAlarmRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::OfflineAlarmRepository) {
        bind<AlarmRepository>()
    }
}