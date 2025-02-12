import java.net.URI

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

var repositoryReleaseUrl: URI = URI.create("https://maven.pkg.github.com/DmitriyCanishev/AnalyticsLibrary")

var libraryGroupId = "com.analytics"
var libraryArtifact = "base"
var libraryVersion = "0.0.1.1"

android {
    namespace = "com.analytics"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

configurations.api.configure {
    isCanBeResolved = true
}

dependencies {
    api("org.jetbrains.kotlin:kotlin-stdlib:2.0.21")
}

tasks.register("copyAARDebug") {
    doLast {
        copy {
            from(File(buildDir.absolutePath, "outputs/aar"))
            include("${project.name}-debug.aar")
            into(File(rootDir.absolutePath, "artifacts/debug"))
        }
    }
}

tasks.register("copyAARRelease") {
    doLast {
        copy {
            from(File(buildDir.absolutePath, "outputs/aar"))
            include("${project.name}-release.aar")
            into(File(rootDir.absolutePath, "artifacts/release"))
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
    publications {
        create<MavenPublication>("maven") {
            groupId = libraryGroupId
            artifactId = libraryArtifact
            version = libraryVersion
            artifact(
                "${
                        File(
                            rootDir.absolutePath,
                            "artifacts/release"
                        )
                }/${project.name}-release.aar"
            )

            pom {
                withXml {
                    val allDependencies =
                        project.configurations.api.get().resolvedConfiguration.firstLevelModuleDependencies
                    val dependenciesNode = asNode().appendNode("dependencies")

                    allDependencies.forEach {
                        val dependency = dependenciesNode.appendNode("dependency")
                        dependency.appendNode("groupId", it.moduleGroup)
                        dependency.appendNode("artifactId", it.moduleName)
                        dependency.appendNode("version", it.moduleVersion)
                    }
                }
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/DmitriyCanishev/AnalyticsLibrary.git"
                    developerConnection = "scm:git:ssh://github.com/DmitriyCanishev/AnalyticsLibrary.git"
                    url = "https://github.com/DmitriyCanishev/AnalyticsLibrary"
                }
            }
        }
    }
}