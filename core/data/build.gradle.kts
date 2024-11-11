plugins {
    alias(libs.plugins.snoozeloo.android.library)
}

android {
    namespace = "com.developerxy.data"
}

dependencies {
    api(projects.core.database)

    implementation(libs.kotlinx.coroutines.core)
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
}