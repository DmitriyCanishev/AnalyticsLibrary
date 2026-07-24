package com.analytics.plugin.factory

import com.analytics.plugin.extension.AnalyticsExtension
import com.analytics.plugin.model.AnalyticsDescriptor
import com.analytics.plugin.model.AnalyticsParameter
import com.analytics.plugin.model.utils.AnalyticsDescriptorBuilder

class AnalyticsFactory(private val extension: AnalyticsExtension) {
    private val _apiKey = "apiKey"

    fun create() : List<AnalyticsDescriptor> {
        val analytics = mutableListOf<AnalyticsDescriptor>()

        for (analytic in extension.analytics) {
            analytics.add(AnalyticsDescriptorBuilder().build(
                artifact = "com.analytics:${analytic.key.analyticsName.value.lowercase()}-sdk:${analytic.key.analyticsVersion}",
                className = "${analytic.key.analyticsName}AnalyticsService",
                parameters = listOf(
                    AnalyticsParameter(
                        name = _apiKey,
                        value = analytic.value.apiKey
                    )
                )
            ))
        }

        return analytics
    }
}