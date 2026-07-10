package com.analytics.common

import com.analytics.logevents.ModuleLogger

object AnalyticsLogger {
    private var _moduleLogger : ModuleLogger? = null

    val Logger: ModuleLogger
        get() =
            _moduleLogger ?:
            synchronized(this) {
                _moduleLogger ?:
                ModuleLogger().also { _moduleLogger = it }
            }
}