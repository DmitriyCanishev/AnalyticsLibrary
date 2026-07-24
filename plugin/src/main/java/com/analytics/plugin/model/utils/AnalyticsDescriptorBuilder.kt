package com.analytics.plugin.model.utils

import com.analytics.plugin.model.AnalyticsDescriptor
import com.analytics.plugin.model.AnalyticsParameter

class AnalyticsDescriptorBuilder {
    fun build(
        artifact: String,
        className: String,
        parameters: List<AnalyticsParameter>
    ): AnalyticsDescriptor {

        val variableName = buildVariableName(className)

        return AnalyticsDescriptor(
            artifact = artifact,
            className = className,
            variableName = variableName,
            parameters = parameters
        )
    }

    private fun buildVariableName(
        className: String
    ): String = className.replaceFirstChar {
        it.lowercase()
    }

}