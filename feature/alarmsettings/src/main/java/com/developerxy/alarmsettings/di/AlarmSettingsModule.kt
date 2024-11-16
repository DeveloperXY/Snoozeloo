package com.developerxy.alarmsettings.di

import com.developerxy.alarmsettings.AlarmSettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val alarmSettingsModule = module {
    viewModelOf(::AlarmSettingsViewModel)
}