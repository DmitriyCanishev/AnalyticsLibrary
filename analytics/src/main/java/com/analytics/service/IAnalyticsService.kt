package com.analytics.service

import android.app.Activity
import android.content.Context
import com.analytics.callback.ServiceCallback
import com.analytics.datatypes.AnalyticsSDKDefinition
import com.analytics.model.AnalyticsEvent

interface IAnalyticsService
{
    fun init(activity: Activity, apiKey: String = "", callback: ServiceCallback? = null)
    fun logEvent(context: Context, event: AnalyticsEvent)
    fun getAnalyticsDefinition() : AnalyticsSDKDefinition
}


