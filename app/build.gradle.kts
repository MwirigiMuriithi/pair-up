// AlAndMa/app/build.gradle.kts
// app/build.gradle.kts

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.alandma"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.alandma"
        minSdk        = 26
        targetSdk     = 35
        versionCode   = 1
        versionName   = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
//    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packagingOptions {
        resources {
            excludes += setOf("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    // -- Compose & Core --
    implementation(libs.android.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.compose.tooling.preview)
    debugImplementation(libs.compose.tooling.debug)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    // -- Navigation (Compose) --
    implementation(libs.navigation.compose)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.compose.foundation.layout)
//    implementation(libs.calendarview)
    implementation(libs.calendar.compose)



    // -- Room (SQLite) --
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
//    kapt(libs.room.compiler)
    ksp(libs.room.compiler)

    // -- Retrofit + OkHttp + Gson --
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp.logging)

//    // -- Hilt (DI) --
//    implementation(libs.hilt.android)
//    kapt(libs.hilt.compiler)
//    implementation(libs.hilt.navigation.compose)

    // -- Coroutines --
    implementation(libs.coroutines.android)

    // -- Lifecycle + ViewModel (Compose) --
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.android.material)

    // -- Logging --
    implementation(libs.timber)

    // -- Testing --
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.espresso.core)
}
