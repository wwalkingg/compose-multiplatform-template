plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    jvm()
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.ktor.client.core)
                implementation(libs.ktor.client.cio)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.client.serialization)
                implementation(libs.ktor.client.logging)
                implementation(libs.logback.classic)
                implementation(libs.kotlinx.serialization.json)
            }
        }
    }
}
