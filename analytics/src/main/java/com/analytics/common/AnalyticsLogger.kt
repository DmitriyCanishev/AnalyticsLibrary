package com.analytics.common

import com.analytics.logevents.ModuleLogger

object AnalyticsLogger {
    private var _moduleLogger : ModuleLogger? = null

    val Logger: ModuleLogger
        get() {
            if (_moduleLogger == null)
                _moduleLogger = ModuleLogger()

            return _moduleLogger!!
        }
}