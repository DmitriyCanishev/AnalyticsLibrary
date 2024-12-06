package com.app.analytics.logevents

import android.util.Log

class ModuleLogger {
    fun d(message: String?) {
        logDebug(message!!)
    }

    fun d(message: String?, message2: String?) {
        logDebug(message!!, message2!!)
    }

    fun e(message: String?) {
        logError(message!!)
    }

    fun e(message: String?, message2: String?) {
        logError(message!!, message2!!)
    }

    private fun logDebug(vararg multipleMessage: Any) {
        val log = StringBuilder()
        for (message in multipleMessage) {
            log.append(message.toString())
        }
        Log.d("AnalyticsService", log.toString())
    }

    private fun logError(vararg multipleMessage: Any) {
        val log = StringBuilder()
        for (message in multipleMessage) {
            log.append(message.toString())
        }
        Log.e("AnalyticsService", log.toString())
    }
}