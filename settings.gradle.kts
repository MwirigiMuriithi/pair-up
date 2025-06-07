// AlAndMa/settings.gradle.kts
// settings.gradle.kts

pluginManagement {
    repositories {
        gradlePluginPortal()  // required for plugins by alias(libs.plugins.…)
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

dependencyResolutionManagement {
    // Prevent module‐level repos: use only those declared below
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "AlAndMa"
include(":app")
