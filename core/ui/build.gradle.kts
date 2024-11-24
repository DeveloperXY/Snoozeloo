plugins {
    alias(libs.plugins.snoozeloo.android.library)
    alias(libs.plugins.snoozeloo.android.library.compose)
}

android {
    namespace = "com.developerxy.ui"
}

dependencies {
    implementation(projects.core.domain)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.navigation.compose)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
}