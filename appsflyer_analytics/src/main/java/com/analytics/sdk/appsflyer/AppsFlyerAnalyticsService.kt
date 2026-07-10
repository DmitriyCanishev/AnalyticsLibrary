package com.analytics.sdk.appsflyer

import android.app.Activity
import android.content.Context
import com.analytics.callback.ServiceCallback
import com.analytics.common.AnalyticsLogger
import com.analytics.datatypes.AnalyticsSDKDefinition
import com.analytics.model.AnalyticsEvent
import com.analytics.service.IAnalyticsService
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener

class AppsFlyerAnalyticsService : IAnalyticsService {
    private val _tag = AppsFlyerAnalyticsService::class.simpleName + "Tag"

    override fun init(activity: Activity, apiKey: String, callback: ServiceCallback?) {
        val context = activity.applicationContext

        try
        {
            AppsFlyerLib.getInstance().init(apiKey, null, context)
            AppsFlyerLib.getInstance().start(context)
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
            AppsFlyerLib.getInstance()
                .logEvent(
                    context,
                    event.eventName,
                    event.params,
                    object : AppsFlyerRequestListener {
                        override fun onSuccess() {
                            AnalyticsLogger.Logger.e("Event send successfully")
                        }

                        override fun onError(p0: Int, p1: String) {
                            AnalyticsLogger.Logger.e(
                                "Event failed to be sent:\nError code: $p0\nError description: $p1"
                            )
                        }
                    })
        } catch (e: Exception){
            throw Exception("Failed to send event with error $e")
        }
    }

    override fun getAnalyticsDefinition(): AnalyticsSDKDefinition =
        AnalyticsSDKDefinition.AppsFlyer
}