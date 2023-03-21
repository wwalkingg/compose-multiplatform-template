import org.jetbrains.compose.compose

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.android.library)
}

group = "buzz.lnmath"
version = "1.0"

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
                implementation(projects.libs.resourceLoader)
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