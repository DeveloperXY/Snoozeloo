plugins {
    alias(libs.plugins.snoozeloo.android.application)
    alias(libs.plugins.snoozeloo.android.application.compose)
}

android {
    namespace = "com.developerxy.app.snoozeloo.catalog"
}

dependencies {
    implementation(projects.core.designsystem)
}