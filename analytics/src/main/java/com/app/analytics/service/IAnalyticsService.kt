package com.app.analytics.service

import android.content.Context
import com.app.analytics.model.AnalyticsEvent

interface IAnalyticsService
{
    fun init(context: Context)
    fun logEvent(event : AnalyticsEvent)
}


