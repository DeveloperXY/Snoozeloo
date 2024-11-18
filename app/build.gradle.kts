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
    implementation(projects.core.domain)
    implementation(projects.feature.youralarms)
    implementation(projects.feature.alarmsettings)
    implementation(projects.feature.alarmtrigger)
    implementation(projects.feature.ringtonesettings)

    implementation(libs.core.splashscreen)
    implementation(libs.androidx.navigation.compose)
}