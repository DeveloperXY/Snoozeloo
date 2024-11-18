plugins {
    alias(libs.plugins.snoozeloo.android.library)
}

android {
    namespace = "com.developerxy.domain"
}

dependencies {
    api(projects.core.data)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
}