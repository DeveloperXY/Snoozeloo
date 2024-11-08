package com.developerxy.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.artifacts.VersionCatalog

fun ApplicationExtension.configureDefaultConfig(libs: VersionCatalog) {
    defaultConfig {
        applicationId = libs.findVersion("projectApplicationId").get().toString()
        minSdk = libs.findVersion("projectMinSdk").get().toString().toInt()
        targetSdk = libs.findVersion("projectTargetSdk").get().toString().toInt()
        versionCode = libs.findVersion("projectVersionCode").get().toString().toInt()
        versionName = libs.findVersion("projectVersionName").get().toString()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
}

fun LibraryExtension.configureDefaultConfig(libs: VersionCatalog) {
    defaultConfig {
        minSdk = libs.findVersion("projectMinSdk").get().toString().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFile("consumer-rules.pro")
    }
}