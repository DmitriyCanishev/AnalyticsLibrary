package com.analytics.service

import com.analytics.common.AnalyticsLogger
import com.analytics.datatypes.AnalyticsSDKDefinition
import com.analytics.model.AnalyticsEvent

class AnalyticsService
{
    private val _tag = AnalyticsService::class.simpleName + "Tag"

    private val _allServices : MutableMap<AnalyticsSDKDefinition, IAnalyticsService?> = mutableMapOf()

    fun init(analyticsList: List<IAnalyticsService>?) {
        try
        {
            AnalyticsLogger.Logger.e("Init AnalyticsService")
            analyticsList?.forEach { analyticService ->
                addService(analyticService)
            }
        } catch (e: Exception) {
            AnalyticsLogger.Logger.d("$_tag: ", "Init failed with Error: $e")
        }
    }

    fun logEvent(event: AnalyticsEvent) {
        try
        {
            _allServices.forEach {
                it.value?.logEvent(event)
            }
        } catch (e: Exception){
            AnalyticsLogger.Logger.e("$_tag: ", "Error during send event : $e")
        }
    }

    private fun addService(service: IAnalyticsService){
        try {
            if (!_allServices.containsKey(service.getAnalyticsDefinition())) {
                AnalyticsLogger.Logger.e("$_tag: ", "Add Service : ${service.javaClass.simpleName}")
                _allServices[service.getAnalyticsDefinition()] = service
            }
        } catch (e : Exception) {
            AnalyticsLogger.Logger.e("$_tag: ", "Add Service - $service failed with error : $e")
        }
    }
}

