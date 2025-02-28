pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AnalyticsModule"
include(":app")
include(":analytics")
include(":yandex_metrica_analytics")
include(":firebase_analytics")
include(":appsflyer_analytics")
