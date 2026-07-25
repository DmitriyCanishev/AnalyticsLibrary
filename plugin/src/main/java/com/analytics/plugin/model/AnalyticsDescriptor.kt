package com.analytics.plugin.model

data class AnalyticsDescriptor(
    val artifact: String,
    val className: String,
    val variableName: String,
    val parameters: List<AnalyticsParameter>?
)
