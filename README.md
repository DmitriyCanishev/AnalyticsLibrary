# Analytics Library module template

## About

To make it easier for you to add the Analytics SDK to your project,
I've created an analytics library that can manage the SDKs and send events to them.

## How use

* First step:
    - Create concrete analytics service into lib 'service' folder
    - Create concrete analytics service as lib, that will use the analytics lib as dependency to implement the IAnalyticsService interface(preferred option)

* Just choose one of it suggestions.

* Second step:
    - Add Concrete Analytics SDK dependencies for it work correctly into project

* Third step:
    - Implement analytics lib into project
```kotlin
 implementation(project(":analytics"))
```

* Fourth step:
    - Create AnalyticsService instance into your project
```kotlin
 private lateinit var _analyticsService: IAnalyticsService
 _analyticsService = AnalyticsService()
```

## Add Concrete Analytics services into analytics lib

* Add Concrete Analytics SDK into a list to AnalyticsService for their managing
```kotlin
 _analyticsService.addService() // Pass as a parameter instance of concrete Analytic service
```

## Init services

```kotlin
 _analyticsService.init(context)
```

## Sending events

* For sending events to Analytics SDK
```kotlin
 _analyticsService.logEvent() // As parameter, use the model class 'AnalyticsEvent' which is suitable for most Analytics SDK
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

