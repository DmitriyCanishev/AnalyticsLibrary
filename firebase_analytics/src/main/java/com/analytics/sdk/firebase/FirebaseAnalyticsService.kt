package com.analytics.sdk.firebase

import android.app.Activity
import com.analytics.common.AnalyticsLogger
import com.analytics.datatypes.AnalyticsSDKDefinition
import com.analytics.model.AnalyticsEvent
import com.analytics.utils.toAnalyticsBundleParams
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseAnalyticsService : IFirebaseAnalyticsService {
    private val _tag = FirebaseAnalyticsService::class.simpleName + "Tag"
    private lateinit var _analyticsTracker: FirebaseAnalytics

    override fun init(activity: Activity) {
        try {
            _analyticsTracker = FirebaseAnalytics.getInstance(activity.applicationContext)
            _analyticsTracker.setAnalyticsCollectionEnabled(true)
        } catch (e: Exception) {
            AnalyticsLogger.Logger.e("Init $_tag failed with error $e")
        }
    }

    override fun init(activity: Activity, apiKey: String): Unit =
        throw Exception("You need use other init method for FirebaseAnalytics")

    override fun logEvent(event: AnalyticsEvent) {
        AnalyticsLogger.Logger.e(
            "Log In $_tag ",
            if (event.params == null)
                "Event : ${event.eventName} in ${this.javaClass}"
            else
                "Event : ${event.eventName} - ${event.params} in ${this.javaClass}"
        )
        try {
            _analyticsTracker.logEvent(
                event.eventName,
                event.params?.toAnalyticsBundleParams()
            )
        } catch (e: Exception) {
            AnalyticsLogger.Logger.e("Failed to send event with error $e")
        }
    }

    override fun getAnalyticsDefinition(): AnalyticsSDKDefinition =
        AnalyticsSDKDefinition.Firebase
}