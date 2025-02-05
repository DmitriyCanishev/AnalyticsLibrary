package com.app.analytics.service

import com.app.analytics.common.AnalyticsLogger
import com.app.analytics.datatypes.AnalyticsSDKDefinition
import com.app.analytics.model.AnalyticsEvent

class AnalyticsService
{
    private val _tag = AnalyticsService::class.simpleName + "Tag"

    private val _allServices : MutableMap<AnalyticsSDKDefinition, IAnalyticsService> = mutableMapOf()

    fun init(analyticsList: List<IAnalyticsService>?) {
        try
        {
            analyticsList?.forEach { analyticService ->
                AnalyticsLogger.Logger.e(
                    "Init ${analyticService.javaClass}"
                )
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
                it.value.logEvent(event)
            }
        } catch (e: Exception){
            AnalyticsLogger.Logger.e("$_tag: ", "Error during send event : $e")
        }
    }

    private fun addService(service: IAnalyticsService){
        try {
            if (!_allServices.containsKey(service.getAnalyticsDefinition())) {
                AnalyticsLogger.Logger.e("$_tag: ", "Init Service : ${service.javaClass.simpleName}")
                _allServices[service.getAnalyticsDefinition()] = service
            }
        } catch (e : Exception) {
            AnalyticsLogger.Logger.e("$_tag: ", "Add Service - $service failed with error : $e")
        }
    }
}

