package com.app.analytics.utils

import android.os.Bundle

fun Map<String, Any>.toAnalyticsBundleParams() : Bundle {
    return Bundle(size).apply {
        onEach { (key, value) ->
            when (value) {
                is String -> putString(key, value)
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
                else ->
                    error("Unsupported param type ${value.javaClass} in key \"$key\"")
            }
        }
    }
}
