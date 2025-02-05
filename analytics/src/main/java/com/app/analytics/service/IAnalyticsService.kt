package com.app.analytics.service

import android.app.Activity
import com.app.analytics.datatypes.AnalyticsSDKDefinition
import com.app.analytics.model.AnalyticsEvent

interface IAnalyticsService
{
    fun init(activity: Activity, apiKey: String = "")
    fun logEvent(event : AnalyticsEvent)
    fun getAnalyticsDefinition() : AnalyticsSDKDefinition
}


