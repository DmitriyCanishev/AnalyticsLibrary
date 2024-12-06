package com.app.analytics.service

import android.content.Context
import com.app.analytics.common.AnalyticsLogger
import com.app.analytics.model.AnalyticsEvent

class AnalyticsService : IAnalyticsService
{
    private val _services : MutableList<IAnalyticsService> = mutableListOf()

    override fun init(context: Context) {
        _services.forEach {
            AnalyticsLogger.Logger.e(
                "Init",
                "Initing ${it.javaClass}")
            it.init(context)
        }
    }

    override fun logEvent(event: AnalyticsEvent) {
        _services.forEach {
            it.logEvent(event)
        }
    }

    fun addService(service: IAnalyticsService){
        require(service !is AnalyticsService) { "Can't add AnalyticsService instance because it container for services" }
        if (!service.serviceExistInList())
            _services.add(service)
    }

    private fun IAnalyticsService.serviceExistInList() : Boolean {
        val checkedServiceClass = this::class.java
        _services.forEach { service ->
            if (checkedServiceClass == service::class.java)
            {
                AnalyticsLogger.Logger.d("$checkedServiceClass equals ${service::class.java} in list")
                return true
            }
        }
        return false
    }
}

