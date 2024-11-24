package com.developerxy.alarmsettings.di

import com.developerxy.alarmsettings.AlarmSettingsViewModel
import com.developerxy.alarmsettings.domain.CreateNewAlarmUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val alarmSettingsModule = module {
    viewModelOf(::AlarmSettingsViewModel)
    factoryOf(::CreateNewAlarmUseCase)
}