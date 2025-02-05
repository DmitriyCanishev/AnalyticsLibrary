package com.app.analytics.sdk.appsflyer

import android.app.Activity
import android.content.Context
import com.app.analytics.common.AnalyticsLogger
import com.app.analytics.datatypes.AnalyticsSDKDefinition
import com.app.analytics.model.AnalyticsEvent
import com.app.analytics.service.IAnalyticsService
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener


class AppsFlyerAnalyticsService : IAnalyticsService {
    private val _tag = AppsFlyerAnalyticsService::class.simpleName + "Tag"

    private lateinit var _context: Context

    override fun init(activity: Activity, apiKey: String) {
        _context = activity.applicationContext

        AppsFlyerLib.getInstance().init(apiKey, null, _context)
        AppsFlyerLib.getInstance().start(_context);
    }

    override fun logEvent(event: AnalyticsEvent) {
        AnalyticsLogger.Logger.e(
            "Log In $_tag",
            if (event.params == null)
                "Event : ${event.eventName} in ${this.javaClass}"
            else
                "Event : ${event.eventName} - ${event.params} in ${this.javaClass}"
        )
        AppsFlyerLib.getInstance()
            .logEvent(
                _context,
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
    }

    override fun getAnalyticsDefinition(): AnalyticsSDKDefinition =
        AnalyticsSDKDefinition.AppsFlyer
}