package %PACKAGE_NAME%

import android.app.Activity
import android.content.Context
import com.analytics.service.AnalyticsService
import com.analytics.callback.ServiceCallback
import com.analytics.model.AnalyticsEvent
import android.util.Log
%IMPORT%
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
                    Log.e("Event:", "Send Success")
                }

                override fun error(message: String?) {
                    Log.e("Event:", "Send failed: $message")
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
                    Log.e("Event:", "Send Success")
                }

                override fun error(message: String?) {
                    Log.e("Event:", "Send failed: $message")
                }
            }
        )
    }

    private fun createService() {
        _analyticsService = AnalyticsService()
    }

    private fun createAnalyticsSdk(activity: Activity) {
%ANALYTIC_DECLARATION%
        _analyticsService.init(
            listOf(
                %ANALYTIC_LIST%
            )
        )
    }
}