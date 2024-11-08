plugins {
    alias(libs.plugins.snoozeloo.android.library)
}

android {
    namespace = "com.developerxy.domain"
}

dependencies {
    implementation(projects.core.data)
}