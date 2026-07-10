package com.analytics.sdk.appmetrica

import android.app.Activity
import android.content.Context
import com.analytics.callback.ServiceCallback
import com.analytics.common.AnalyticsLogger
import com.analytics.datatypes.AnalyticsSDKDefinition
import com.analytics.model.AnalyticsEvent
import com.analytics.service.IAnalyticsService
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig

class AppMetricaAnalyticsService : IAnalyticsService {
    private val _tag = AppMetricaAnalyticsService::class.simpleName+ "Tag"

    override fun init(activity: Activity, apiKey: String, callback: ServiceCallback?) {
        try
        {
            AppMetrica
                .activate(
                    activity.applicationContext, AppMetricaConfig
                        .newConfigBuilder(apiKey)
                        .build()
                )
            AppMetrica.enableActivityAutoTracking(activity.application)
            callback?.success()
        } catch (e: Exception){
            AnalyticsLogger.Logger.e("Init $_tag failed with error $e")
            callback?.error("Init $_tag error $e")
        }
    }

    override fun logEvent(context: Context, event: AnalyticsEvent) {
        AnalyticsLogger.Logger.e(
            "Log In $_tag ",
            if (event.params == null)
                "Event : ${event.eventName} in ${this.javaClass}"
            else
                "Event : ${event.eventName} - ${event.params} in ${this.javaClass}"
        )
        try
        {
            when (event.params) {
                null -> {
                    AppMetrica.reportEvent(event.eventName)
                }

                else -> {
                    AppMetrica.reportEvent(event.eventName, event.params)
                }
            }
        } catch (e: Exception){
            throw Exception("Failed to send event with error $e")
        }
    }

    override fun getAnalyticsDefinition(): AnalyticsSDKDefinition =
        AnalyticsSDKDefinition.AppMetrica
}