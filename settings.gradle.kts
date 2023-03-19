enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}

rootProject.name = "compose-multiplatform-template"

include(":android")
include(":desktop")
include(":libs:resourceLoader")
include(":libs:image-picker")
include(":libs:async-image")

include(":core:ui")
include(":core:util")
include(":core:model")
include(":core:network")
include(":core:datastore")


include(":feature:home")