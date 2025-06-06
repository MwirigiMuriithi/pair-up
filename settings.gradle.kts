// AlAndMa/settings.gradle.kts
// settings.gradle.kts

pluginManagement {
    repositories {
        gradlePluginPortal()  // required for plugins by alias(libs.plugins.…)
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    // Prevent module‐level repos: use only those declared below
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AlAndMa"
include(":app")
