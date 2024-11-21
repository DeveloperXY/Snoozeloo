package com.developerxy.youralarms.di

import com.developerxy.youralarms.YourAlarmsViewModel
import com.developerxy.youralarms.domain.FetchYourAlarmsUseCase
import com.developerxy.youralarms.domain.ToggleAlarmActiveStateUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val yourAlarmsModule = module {
    factoryOf(::FetchYourAlarmsUseCase)
    factoryOf(::ToggleAlarmActiveStateUseCase)
    viewModelOf(::YourAlarmsViewModel)
}