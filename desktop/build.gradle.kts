plugins {
    alias(libs.plugins.kotlin.multiplatform)
//    alias(libs.plugins.compose.multiplatform)
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
//                implementation(compose.desktop.currentOs)
                implementation(libs.decompose)
                implementation(libs.decompose.compose.multiplatform)
            }
        }
    }
}
//compose.desktop {
////    application {
////        mainClass = "MainKt"
////        nativeDistributions {
////            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
////            packageName = "compose-multiplatform-template"
////            packageVersion = "1.0.0"
////        }
////    }
//}
