package com.analytics.callback

interface ServiceCallback {
    fun success()
    fun error(message: String?)
}
