import org.jetbrains.compose.compose

plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
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
        val desktopMain by getting {
            dependencies {

            }
        }
    }
}