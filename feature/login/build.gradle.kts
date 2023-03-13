plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.android.library)
}

@OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
kotlin {
    android()
    jvm("desktop") {
        jvmToolchain(11)
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(projects.libs.resourceLoader)
                implementation(libs.decompose)
                implementation(projects.core.model)
                implementation(projects.core.datastore)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.core.ktx)
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.preview)
            }
        }
    }
}

android {
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res","src/commonMain/resources")
    defaultConfig {
        minSdk = 24
        compileSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}