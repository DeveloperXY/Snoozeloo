package com.developerxy.di

import com.developerxy.ui.RingtonesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedAlarmSettingsModule = module {
    viewModelOf(::RingtonesViewModel)
}