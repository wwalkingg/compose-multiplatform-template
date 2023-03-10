import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
}

group = "buzz.lnmath"
version = "1.0"


kotlin {
    jvm {
        jvmToolchain(17)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(projects.core.ui)
                implementation(compose.desktop.currentOs)
            }
        }
        val jvmTest by getting
        val commonMain by getting {
            dependencies {

            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "compose-multiplatform-template"
            packageVersion = "1.0.0"
        }
    }
}
