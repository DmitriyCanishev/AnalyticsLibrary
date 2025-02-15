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

## Major Notes

For using Firebase Analytics you need add **google-services.json** to project folder:
   - For Android: app/
   - For Unity: Assets/

For using Firebase Analytics in Unity use Unity 2021 LTS or Later.

Other Analytics services work fine and on Unity 2020.

For Unity writes a separate class for manage analytics sdk services : `UnityAnalyticsService`
Example project in Unity to demonstrate using library into Unity([See](https://github.com/DmitriyCanishev/UseAnalyticsLibraryInUnity))

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
val concreteAnalytics = IAnalyticsServiceImpl().also {
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
    AnalyticsEvent(
        eventName = "FirstCustomEvent", 
        params = mapOf("EventParam" to "EventParamValue")) //params:Map<String, Any>? can be null
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
AnalyticsLogger.e("$message")
AnalyticsLogger.Logger.e("$message", "$message")
```

## Versions of analytics sdk ##

* AppsFlyer : com.appsflyer:af-android-sdk:6.12.1
* Firebase : com.google.firebase:firebase-analytics:22.2.0
* AppMetrica : io.appmetrica.analytics:analytics:7.6.0