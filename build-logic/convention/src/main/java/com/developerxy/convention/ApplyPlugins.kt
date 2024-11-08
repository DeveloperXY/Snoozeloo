package com.developerxy.convention

import org.gradle.api.Project

fun Project.applyPlugin(pluginId: String) {
    pluginManager.run {
        apply(libs.findPlugin(pluginId).get().get().pluginId)
    }
}

fun Project.applyPlugins(vararg pluginIds: String) {
    pluginIds.forEach {
        applyPlugin(it)
    }
}