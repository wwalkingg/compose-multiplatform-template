import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.compose.desktop.DesktopExtension
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

class DesktopApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("org.jetbrains.compose")
            }
            extensions.configure<DesktopExtension> {
                application {
                    mainClass = "MainKt"
                    nativeDistributions {
                        targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
                        // todo
                        packageName = "compose-multiplatform-template"
                        packageVersion = "1.0.0"
                    }
                }
            }
        }
    }
}