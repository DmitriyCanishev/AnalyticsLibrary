package com.analytics.service

import android.app.Activity
import com.analytics.datatypes.AnalyticsSDKDefinition
import com.analytics.model.AnalyticsEvent

interface IAnalyticsService
{
    fun init(activity: Activity, apiKey: String = "")
    fun logEvent(event : AnalyticsEvent)
    fun getAnalyticsDefinition() : AnalyticsSDKDefinition
}


