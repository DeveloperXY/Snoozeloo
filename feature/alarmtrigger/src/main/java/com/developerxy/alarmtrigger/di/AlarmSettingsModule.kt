package com.developerxy.alarmtrigger.di

import com.developerxy.alarmtrigger.domain.GetAlarmDetailsUseCase
import com.developerxy.alarmtrigger.ui.AlarmTriggerViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val alarmTriggerModule = module {
    factoryOf(::GetAlarmDetailsUseCase)
    viewModelOf(::AlarmTriggerViewModel)
}