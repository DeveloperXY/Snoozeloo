plugins {
    alias(libs.plugins.snoozeloo.android.application)
    alias(libs.plugins.snoozeloo.android.application.compose)
}

android {
    namespace = "com.developerxy.snoozeloo"
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.designsystem)
    implementation(projects.feature.youralarms)
    implementation(projects.feature.alarmsettings)
    implementation(projects.feature.alarmtrigger)

    implementation(libs.core.splashscreen)
    implementation(libs.androidx.navigation.compose)
}