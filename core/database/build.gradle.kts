plugins {
    alias(libs.plugins.snoozeloo.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

android {
    namespace = "com.developerxy.database"

    ksp {
        arg("room.generateKotlin", "true")
    }

    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {
    api(projects.core.model)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
}