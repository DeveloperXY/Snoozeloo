plugins {
    alias(libs.plugins.snoozeloo.android.feature)
    alias(libs.plugins.snoozeloo.android.library)
    alias(libs.plugins.snoozeloo.android.library.compose)
}

android {
    namespace = "com.developerxy.alarmsettings"
}

dependencies {
    implementation(projects.core.domain)
}