package PACKAGE

import android.app.Activity
import android.content.Context
import com.analytics.service.AnalyticsService
import com.analytics.callback.ServiceCallback
import com.analytics.model.AnalyticsEvent
import com.analytics.common.AnalyticsLogger
import com.analytics.sdk.appmetrica.AppMetricaAnalyticsService
import com.analytics.sdk.firebase.FirebaseAnalyticsService
import com.analytics.sdk.appsflyer.AppsFlyerAnalyticsService

class AnalyticsServiceHandler {
    private lateinit var _analyticsService: AnalyticsService

    fun init(activity: Activity) {
        createService()
        createAnalyticsSdk(activity)
    }

    fun logEventWithoutParams(context: Context, eventName: String) {
        _analyticsService.logEvent(
            context = context,
            event = AnalyticsEvent(eventName = eventName),
            callback = object : ServiceCallback {
                override fun success() {
                    AnalyticsLogger.Logger.e("Event:", "Send Success")
                }

                override fun error(message: String?) {
                    AnalyticsLogger.Logger.e("Event:", "Send failed: $message")
                }
            }
        )
    }

    fun logEventWithParams(
        context: Context,
        eventName: String,
        eventParam: String,
        eventValue: Any
    ) {
        _analyticsService.logEvent(
            context = context,
            event = AnalyticsEvent(
                eventName = eventName,
                params = mapOf(eventParam to eventValue)
            ),
            callback = object : ServiceCallback {
                override fun success() {
                    AnalyticsLogger.Logger.e("Event:", "Send Success")
                }

                override fun error(message: String?) {
                    AnalyticsLogger.Logger.e("Event:", "Send failed: $message")
                }
            }
        )
    }

    private fun createService() {
        _analyticsService = AnalyticsService()
    }

    private fun createAnalyticsSdk(activity: Activity) {
        val appMetricaAnalyticsService = AppMetricaAnalyticsService().also {
            it.init(
                activity = activity,
                apiKey = "",
                callback = object : ServiceCallback {
                    override fun success() {
                        AnalyticsLogger.Logger.e("ConcreteAnalytics:", "Init Success")
                    }

                    override fun error(message: String?) {
                        AnalyticsLogger.Logger.e("ConcreteAnalytics:", "Init failed: $message")
                    }
                }
            )
        }

        val firebaseAnalyticsService = FirebaseAnalyticsService().also {
            it.init(
                activity = activity,
                callback = object : ServiceCallback {
                    override fun success() {
                        AnalyticsLogger.Logger.e("ConcreteAnalytics:", "Init Success")
                    }

                    override fun error(message: String?) {
                        AnalyticsLogger.Logger.e("ConcreteAnalytics:", "Init failed: $message")
                    }
                }
            )
        }

        val appsFlyerAnalyticsService = AppsFlyerAnalyticsService().also {
            it.init(
                activity = activity,
                apiKey = "",
                callback = object : ServiceCallback {
                    override fun success() {
                        AnalyticsLogger.Logger.e("ConcreteAnalytics:", "Init Success")
                    }

                    override fun error(message: String?) {
                        AnalyticsLogger.Logger.e("ConcreteAnalytics:", "Init failed: $message")
                    }
                }
            )
        }

        _analyticsService.init(
            listOf(
                appMetricaAnalyticsService,
                firebaseAnalyticsService,
                appsFlyerAnalyticsService
            )
        )
    }
}