package com.analytics.service

import android.app.Activity
import android.content.Context
import com.analytics.callback.ServiceCallback
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
            AnalyticsLogger.Logger.e("$_tag: ", "Init failed with Error: $e")
        }
    }

    fun logEvent(context: Context, event: AnalyticsEvent, callback: ServiceCallback? = null) {
        try
        {
            _allServices.forEach {
                it.value?.logEvent(context, event)
            }
            callback?.success()
        } catch (e: Exception){
            AnalyticsLogger.Logger.e("$_tag: ", "Error during send event : $e")
            callback?.error("Error: $e")
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

