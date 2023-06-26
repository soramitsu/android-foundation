enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

rootProject.name = "android-foundation"

include(
    ":runtime-permission"
)

includeBuild("conventionPlugins")