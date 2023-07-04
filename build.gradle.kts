plugins {
    id("com.android.library")
    kotlin("android")
}

val androidAppCompatVersion = "1.6.1"

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        kotlinOptions {
            freeCompilerArgs += arrayOf(
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
            )
        }
    }

    namespace = "jp.co.soramitsu.android_foundation"
}

dependencies {
    implementation("androidx.appcompat:appcompat:$androidAppCompatVersion")
}