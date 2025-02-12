# Analytics Library module template

## About

To make it easier for you to add the Analytics SDK to your project,
I've created an analytics library that can manage the SDKs and send events to them.

## Integration

* Add 'Analytics' repository URL
```gradle
allprojects {
    repositories {
        maven {
          url "https://maven.pkg.github.com/DmitriyCanishev/AnalyticsLibrary"
          credentials {
              username = "GITHUB_USERNAME"
              password = "GITHUB_TOKEN"
          }
	    }
    }
}
```

* Add 'Analytics' dependency for Android or Unity projects
```gradle
implementation ("com.analytics:base:+")
```

* Add dependency for analytics sdk what you will need in projects, but first of all you need add Analytics dependency
```gradle
implementation("com.analytics:appsflyer-sdk:+")
implementation("com.analytics:firebase-sdk:+")
implementation("com.analytics:appmetrica-sdk:+")
```

## Settings Analytics Service before use

* Declare variable of AnalyticsService
```kotlin
 private lateinit var _analyticsService: AnalyticsService
```

* Create instance of AnalyticsService
```kotlin
 _analyticsService = AnalyticsService()
```

* Create analytics sdk instances
```kotlin
val concreteAnalytics = IAnalyticsSericeImpl().also {
    it.init(
        activity,
        "apiKey" // It has a default value, so if the adapter doesn't need this identifier(like Firebase), just fill the first parameter.
    )
}
```

* Add Concrete Analytics SDK into a list to AnalyticsService for their managing
```kotlin
 _analyticsService.init(listOf()) // Here, Example: listOf(appMetricaAnalytics, firebaseAnalytics)
```

## Sending events

* For sending events to Analytics SDK
```kotlin
 _analyticsService.logEvent(
    AnalyticsEvent(eventName = "FirstCustomEvent")
 ) // As parameter, use the model class 'AnalyticsEvent' which is suitable for most Analytics SDK
```

## Debugging

* Use a custom Logger to sort Logs by tag - "AnalyticsService"

* Debug variants
```kotlin
AnalyticsLogger.Logger.d("$message")
AnalyticsLogger.Logger.d("$message", "$message")
```

* Error variants
```kotlin
AnalyticsLogg.e("$message")
AnalyticsLogger.Logger.e("$message", "$message")
```

## Versions of mediators ##

* AppsFlyer : com.appsflyer:af-android-sdk:6.12.1
* Firebase : com.google.firebase:firebase-analytics:22.2.0
* AppMetrica : io.appmetrica.analytics:analytics:7.6.0