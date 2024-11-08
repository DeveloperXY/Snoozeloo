plugins {
    alias(libs.plugins.snoozeloo.android.application)
    alias(libs.plugins.snoozeloo.android.application.compose)
}

android {
    namespace = "com.developerxy.snoozeloo"
}

dependencies {
    implementation(projects.feature.youralarms)
    implementation(projects.feature.alarmsettings)
    implementation(projects.feature.alarmtrigger)
}