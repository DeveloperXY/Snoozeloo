package com.developerxy.ringtonesettings.di

import com.developerxy.ringtonesettings.RingtonePickerViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val ringtonePickerModule = module {
    viewModelOf(::RingtonePickerViewModel)
}