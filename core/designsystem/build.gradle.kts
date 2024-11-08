plugins {
    alias(libs.plugins.snoozeloo.android.library)
}

android {
    namespace = "com.developerxy.designsystem"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}