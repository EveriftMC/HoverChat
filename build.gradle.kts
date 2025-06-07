plugins {
    id("java")
}

group = "com.yxxngtai"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.extendedclip.com/releases/")
}

dependencies {
    compileOnly("io.papermc.paper", "paper-api", "1.21.5-R0.1-SNAPSHOT")
    compileOnly("me.clip", "placeholderapi", "2.11.6")
}

tasks.processResources {
    filesMatching("plugin.yml") {
        expand(
            "version" to version
        )
    }
}
