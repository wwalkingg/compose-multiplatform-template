plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.android.library)
}

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
                api(projects.core.network)
            }
        }
        val androidMain by getting {
            dependencies {
                compileOnly(libs.androidx.appcompat)
            }
        }
        val desktopMain by getting {
            dependencies {
                compileOnly(compose.preview)
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}