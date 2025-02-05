package com.app.analytics.sdk.firebase

import android.app.Activity
import com.app.analytics.common.AnalyticsLogger
import com.app.analytics.datatypes.AnalyticsSDKDefinition
import com.app.analytics.model.AnalyticsEvent
import com.app.analytics.utils.toAnalyticsBundleParams
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseAnalyticsService : IFirebaseAnalyticsService {
    private val _tag = FirebaseAnalyticsService::class.simpleName+ "Tag"
    private lateinit var _analyticsTracker: FirebaseAnalytics

    override fun init(activity: Activity) {
        _analyticsTracker = FirebaseAnalytics.getInstance(activity.applicationContext)
        _analyticsTracker.setAnalyticsCollectionEnabled(true)
    }

    override fun init(activity: Activity, apiKey: String): Unit =
        throw Exception("You need use other init method for FirebaseAnalytics")

    override fun logEvent(event: AnalyticsEvent) {
        AnalyticsLogger.Logger.e(
            "Log In $_tag",
            if (event.params == null)
                "Event : ${event.eventName} in ${this.javaClass}"
            else
                "Event : ${event.eventName} - ${event.params} in ${this.javaClass}"
        )
        _analyticsTracker.logEvent(event.eventName, event.params?.toAnalyticsBundleParams())
    }

    override fun getAnalyticsDefinition(): AnalyticsSDKDefinition =
        AnalyticsSDKDefinition.Firebase
}