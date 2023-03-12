plugins {
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(projects.core.ui)
    implementation(libs.decompose)
    implementation(libs.decompose.compose.android)
}

android {
    defaultConfig {
        applicationId = "buzz.lnmath.android"
        minSdk = 24
        compileSdk = 33
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}