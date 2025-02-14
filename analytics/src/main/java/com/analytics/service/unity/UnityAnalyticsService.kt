package com.analytics.service.unity

import com.analytics.model.AnalyticsEvent
import com.analytics.service.AnalyticsService
import com.analytics.service.IAnalyticsService

class UnityAnalyticsService {

    private val _analyticsService = AnalyticsService()
    private val _analyticsList : MutableList<IAnalyticsService>?= mutableListOf()

    fun init(analyticsList: List<IAnalyticsService>){
        _analyticsService.init(analyticsList)
    }

    fun collectAnalytics(analyticsService: IAnalyticsService){
        _analyticsList?.add(analyticsService)
    }

    fun logEvent(eventName: String, eventParams: Map<String, Any>? = null){
        _analyticsService.logEvent(AnalyticsEvent(eventName, eventParams))
    }
}