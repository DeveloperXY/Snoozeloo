import com.android.build.api.dsl.LibraryExtension
import com.developerxy.convention.applyPlugins
import com.developerxy.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            applyPlugins(
                "android-library",
                "compose-compiler"
            )

            extensions.configure<LibraryExtension> {
                configureAndroidCompose(this)
            }
        }
    }
}