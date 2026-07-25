package com.analytics.plugin.generator

import com.analytics.plugin.model.AnalyticsDescriptor

class AnalyticsServiceGenerator {
    fun generate(
        template: String,
        packageName: String,
        analytics: List<AnalyticsDescriptor>
    ): String {
        return template
            .replace("%PACKAGE_NAME%", packageName)
            .replace("%IMPORT%", buildImport(analytics))
            .replace("%ANALYTIC_DECLARATION%", buildDeclarations(analytics))
            .replace("%ANALYTIC_LIST%", buildAdapterList(analytics))

    }

    private fun buildImport(analytics: List<AnalyticsDescriptor>): String {
        return buildString {
            analytics.forEach { analytics ->
                appendLine(
                    "import com.analytics.sdk.${
                        analytics.variableName.replace(
                            "AnalyticsService",
                            ""
                        ).lowercase()
                    }.${analytics.className}"
                )
            }
        }
    }

    private fun buildDeclarations(analytics: List<AnalyticsDescriptor>): String {
        return buildString {
            analytics.forEach { analytic ->
                appendLine(buildDeclaration(analytic))
            }
        }
    }

    private fun buildDeclaration(analytic: AnalyticsDescriptor): String {
        return buildString {
            appendLine(
                "        val ${analytic.variableName} = ${analytic.className}().also {"
            )

            appendLine(
                "            it.init("
            )

            appendLine(
                "                activity = activity,"
            )

            analytic.parameters?.forEachIndexed { _, parameter ->
                appendLine(
                    "                ${parameter.name} = \"${parameter.value}\","
                )
            }

            appendLine("                callback = object : ServiceCallback {")
            appendLine("                    override fun success() {")
            appendLine("                        AnalyticsLogger.Logger.e(\"ConcreteAnalytics:\", \"Init Success\")")
            appendLine("                    }")
            appendLine()
            appendLine("                    override fun error(message: String?) {")
            appendLine("                        AnalyticsLogger.Logger.e(\"ConcreteAnalytics:\", \"Init failed: \$message \")")
            appendLine("                    }")
            appendLine("                }")
            appendLine("            )")
            append(
                "        }"
            )
        }
    }

    private fun buildAdapterList(analytics: List<AnalyticsDescriptor>): String {
        return analytics.joinToString(separator = ",\n                ") {
            it.variableName
        }
    }
}