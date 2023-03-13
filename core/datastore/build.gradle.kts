import org.jetbrains.compose.compose

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
}

kotlin {
    android()
    jvm("desktop") {
        jvmToolchain(11)
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.model)
                api(libs.multiplatformSettings)
                api(libs.multiplatformSettings.noArg)
                api(libs.multiplatformSettings.coroutines)
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

            }
        }
    }
}

android {
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        compileSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}