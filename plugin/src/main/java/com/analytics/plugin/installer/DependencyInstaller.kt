package com.analytics.plugin.installer

import com.analytics.plugin.model.AnalyticsDescriptor
import org.gradle.api.Project

class DependencyInstaller(private val target: Project) {
    fun install(analytics: List<AnalyticsDescriptor>) {
        target.logger.lifecycle("---Installing AnalyticsSDK dependencies---")

        addDependency("com.analytics:base:0.0.2")
        analytics.forEach { analytic ->
            addDependency(analytic.artifact)
        }

        target.logger.lifecycle("All dependencies installed")
    }

    private fun addDependency(dependency: String) {
        target.dependencies.add("implementation", dependency)
    }
}
