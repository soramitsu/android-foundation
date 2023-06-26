allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://nexus.iroha.tech/repository/maven-soramitsu/")
        mavenLocal()
    }
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
        classpath(libs.gradleplugins.android)
        classpath(libs.gradleplugins.kotlin)
    }
}