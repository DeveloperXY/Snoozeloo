package com.developerxy.di

import com.developerxy.RingtonesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedAlarmSettingsModule = module {
    viewModelOf(::RingtonesViewModel)
}