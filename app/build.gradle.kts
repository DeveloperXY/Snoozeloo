plugins {
    alias(libs.plugins.snoozeloo.android.application)
    alias(libs.plugins.snoozeloo.android.application.compose)
}

android {
    namespace = "com.developerxy.snoozeloo"
}

dependencies {
    implementation(libs.core.splashscreen)
    implementation(projects.core.designsystem)
    implementation(projects.feature.youralarms)
    implementation(projects.feature.alarmsettings)
    implementation(projects.feature.alarmtrigger)
}