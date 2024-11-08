import com.android.build.api.dsl.ApplicationExtension
import com.developerxy.convention.configureAndroidCompose
import com.developerxy.convention.applyPlugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            applyPlugins(
                "android-application",
                "compose-compiler"
            )

            extensions.configure<ApplicationExtension> {
                configureAndroidCompose(this)
            }
        }
    }
}