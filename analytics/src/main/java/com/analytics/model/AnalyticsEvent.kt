package com.analytics.model

data class AnalyticsEvent(
    val eventName: String = "EventName",
    val params: Map<String, Any>? = null
) {
    override fun toString(): String = "${this.javaClass.simpleName}(eventName=$eventName)"
}