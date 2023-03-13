plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.parcelize)
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
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material3)
                implementation(libs.decompose)
                implementation(libs.decompose.compose.multiplatform)
                api(libs.kotlinx.coroutines.core)
                api(projects.feature.home)
                api(projects.feature.login)
                implementation(projects.core.network)
                implementation(projects.core.model)
                implementation(projects.core.datastore)
                implementation(projects.libs.resourceLoader)
                implementation(libs.accompanist.pager)
                implementation(projects.feature.allCourse)
                implementation(projects.feature.courseDetail)
                implementation(projects.feature.statusPage)
                implementation(projects.feature.search)
                implementation(projects.feature.me)
            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.androidx.appcompat)
                api(libs.androidx.core.ktx)
                implementation(libs.kotlinx.coroutines.android)
            }
        }
        val desktopMain by getting {
            dependencies {
                api(compose.preview)
                implementation(libs.kotlinx.coroutines.swing)
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