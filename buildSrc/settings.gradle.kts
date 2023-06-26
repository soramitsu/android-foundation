enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "android-foundation"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}