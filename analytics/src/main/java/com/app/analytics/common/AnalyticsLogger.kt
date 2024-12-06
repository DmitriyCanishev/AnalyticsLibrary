package com.app.analytics.common

import com.app.analytics.logevents.ModuleLogger

object AnalyticsLogger {
    private var _moduleLogger : ModuleLogger? = null

    val Logger: ModuleLogger
        get() {
            if (_moduleLogger == null)
                _moduleLogger = ModuleLogger()

            return _moduleLogger!!
        }
}