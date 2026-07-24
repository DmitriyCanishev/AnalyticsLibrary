import java.net.URI

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("java-gradle-plugin")
    id("maven-publish")
}

var repositoryReleaseUrl: URI = URI.create("https://maven.pkg.github.com/DmitriyCanishev/AnalyticsLibrary")

var libraryGroupId = "com.analytics.plugin"
var libraryVersion = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    implementation("com.android.tools.build:gradle:8.2.2")
}

gradlePlugin {
    plugins {
        create("AnalyticsPlugin") {
            group = libraryGroupId
            version = libraryVersion

            id = "com.analytics.plugin"
            implementationClass = "com.analytics.plugin.AnalyticsPlugin"
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = repositoryReleaseUrl

            credentials {
                username = System.getenv("PACKAGE_UPLOAD_USER")
                password = System.getenv("PACKAGE_UPLOAD_PASSWORD")
            }
        }
    }
}
