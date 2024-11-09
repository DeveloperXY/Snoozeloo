plugins {
    alias(libs.plugins.snoozeloo.android.library)
    alias(libs.plugins.snoozeloo.android.library.compose)
}

android {
    namespace = "com.developerxy.designsystem"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.google.fonts)
}