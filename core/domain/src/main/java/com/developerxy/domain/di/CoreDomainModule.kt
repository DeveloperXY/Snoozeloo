package com.developerxy.domain.di

import com.developerxy.domain.FetchDefaultRingtonesUseCase
import com.developerxy.domain.ScheduleAlarmUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val coreDomainModule = module {
    factoryOf(::FetchDefaultRingtonesUseCase)
    factoryOf(::ScheduleAlarmUseCase)
}