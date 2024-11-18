package com.developerxy.domain.di

import com.developerxy.domain.FetchDefaultRingtonesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val coreDomainModule = module {
    factoryOf(::FetchDefaultRingtonesUseCase)
}