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