import com.android.build.api.dsl.LibraryExtension
import com.developerxy.convention.applyPlugins
import com.developerxy.convention.configureAndroidCompose
import com.developerxy.convention.configureBuildTypes
import com.developerxy.convention.configureDefaultConfig
import com.developerxy.convention.configureKotlinAndroid
import com.developerxy.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            applyPlugins(
                "android-library",
                "kotlin-android",
            )

            extensions.configure<LibraryExtension> {
                compileSdk = libs.findVersion("projectCompileSdk").get().toString().toInt()

                configureDefaultConfig(libs)
                configureBuildTypes()
                configureKotlinAndroid(this)

                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
            }
        }
    }
}