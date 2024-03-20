@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("maven-publish")
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

kotlin {
    jvmToolchain(11)
}

android {
    namespace = "jp.co.soramitsu.androidfoundation"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.compose.runtime:runtime:1.5.4")
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("com.google.android.material:material:1.10.0")
    implementation("junit:junit:4.13.2")
}
