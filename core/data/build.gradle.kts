plugins {
    alias(libs.plugins.snoozeloo.android.library)
}

android {
    namespace = "com.developerxy.data"
}

dependencies {
    implementation(projects.core.database)
}