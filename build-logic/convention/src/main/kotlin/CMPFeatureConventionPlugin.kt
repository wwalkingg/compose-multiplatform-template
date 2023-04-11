import app.configureKotlinAndroid
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class CMPFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kotlin-multiplatform")
                apply("org.jetbrains.compose")
                apply("com.android.library")
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            // sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }
            extensions.configure<KotlinMultiplatformExtension> {
                android()
                jvm("desktop") {
                    jvmToolchain(17)
                }
                sourceSets.maybeCreate("commonMain").dependencies {
                    implementation(libs.findLibrary("compose.runtime").get())
                    implementation(libs.findLibrary("compose.foundation").get())
                    implementation(libs.findLibrary("compose.material3").get())
                }
                sourceSets.maybeCreate("androidMain").dependencies {
                    implementation(libs.findLibrary("androidx.appcompat").get())
                    implementation(libs.findLibrary("androidx.core.ktx").get())
                }
                sourceSets.maybeCreate("desktopMain").dependencies {
                    api(libs.findLibrary("compose.preview").get())
                }
            }
        }
    }
}