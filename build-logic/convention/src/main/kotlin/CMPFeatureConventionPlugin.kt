import app.configureKotlinAndroid
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

class CMPFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kotlin-multiplatform")
                apply("org.jetbrains.compose")
                apply("com.android.library")
            }
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }
            extensions.configure(Kotlin)
            kotlin {
                android()
                jvm("desktop") {
                    jvmToolchain(17)
                }
                sourceSets {
                    val commonMain by getting {
                        dependencies {
                            api(compose.runtime)
                            api(compose.foundation)
                            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                            api(compose.material3)
                            implementation(projects.feature.statusPage)
                            implementation(projects.core.model)
                        }
                    }
                    val androidMain by getting {
                        dependencies {
                            api(libs.androidx.appcompat)
                            api(libs.androidx.core.ktx)
                        }
                    }
                    val desktopMain by getting {
                        dependencies {
                            api(compose.preview)
                        }
                    }
                }
            }
            android {
                compileSdkVersion(33)
                sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
                defaultConfig {
                    minSdkVersion(24)
                    targetSdkVersion(33)
                }
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }
            }
        }
    }
}