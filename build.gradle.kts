plugins {
    id("java")
    id("xyz.jpenilla.run-paper") version "2.3.1"
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

tasks {
    runServer {
        minecraftVersion("1.21.5")
        
        downloadPlugins.hangar("PlaceholderAPI", "2.11.6")
        
        jvmArgs("-Xmx2G", "-Xms2G", "-Dcom.mojang.eula.agree=true")
    }

    processResources {
        filesMatching("paper-plugin.yml") {
            expand(
                "version" to version
            )
        }
    }
}