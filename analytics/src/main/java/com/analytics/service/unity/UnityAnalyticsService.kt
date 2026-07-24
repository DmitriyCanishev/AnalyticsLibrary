package com.analytics.service.unity

import android.content.Context
import com.analytics.model.AnalyticsEvent
import com.analytics.service.AnalyticsService
import com.analytics.service.IAnalyticsService

class UnityAnalyticsService {

    private val _analyticsService = AnalyticsService()
    private val _analyticsList : MutableList<IAnalyticsService>?= mutableListOf()

    fun init() =
        _analyticsService.init(_analyticsList)

    fun collectAnalytics(analyticsService: IAnalyticsService){
        _analyticsList?.add(analyticsService)
    }

    fun logEvent(context: Context, eventName: String, eventParams: Map<String, Any>? = null) =
        _analyticsService.logEvent(context, AnalyticsEvent(eventName, eventParams))
}