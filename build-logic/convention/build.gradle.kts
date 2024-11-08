plugins {
    `kotlin-dsl`
}

group = "com.developerxy.snoozeloo.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin) // provides ApplicationExtension
    compileOnly(libs.kotlin.gradlePlugin) // provides the KotlinCompile class which allows us to configure Kotlin project wide
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "snoozeloo.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "snoozeloo.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidFeature") {
            id = "snoozeloo.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidLibrary") {
            id = "snoozeloo.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "snoozeloo.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
    }
}