

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://nexus.iroha.tech/repository/maven-soramitsu/")
        mavenLocal()
    }

    val androidAppCompatVersion = "1.6.1"
    extra["androidAppCompat"] = "androidx.appcompat:appcompat:$androidAppCompatVersion"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

buildscript {

    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()

        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21")
        classpath("com.android.tools.build:gradle:7.4.2")
    }
}