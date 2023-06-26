plugins {
    id("com.android.library")
    kotlin("android")
}

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

    namespace = "jp.co.soramitsu.foundation.runtime_permission"
}

dependencies {
    val androidAppCompat = parent!!.extra["androidAppCompat"].toString()
    implementation(androidAppCompat)
}