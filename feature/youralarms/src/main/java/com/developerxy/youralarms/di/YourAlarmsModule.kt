package com.developerxy.youralarms.di

import com.developerxy.youralarms.YourAlarmsViewModel
import com.developerxy.youralarms.domain.FetchYourAlarmsUseCase
import com.developerxy.youralarms.domain.ToggleAlarmActiveStateUseCase
import com.developerxy.youralarms.domain.DeleteAlarmUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val yourAlarmsModule = module {
    factoryOf(::FetchYourAlarmsUseCase)
    factoryOf(::DeleteAlarmUseCase)
    factoryOf(::ToggleAlarmActiveStateUseCase)
    viewModelOf(::YourAlarmsViewModel)
}