package com.analytics.plugin

import com.analytics.plugin.extension.AnalyticsExtension
import com.analytics.plugin.factory.AnalyticsFactory
import com.analytics.plugin.installer.DependencyInstaller
import com.analytics.plugin.installer.TemplateInstaller
import org.gradle.api.Plugin
import org.gradle.api.Project

class AnalyticsPlugin : Plugin<Project>{
    override fun apply(target: Project) {
        target.plugins.withId("com.android.application") {
            target.logger.lifecycle("---AnalyticsPlugin applied to ${target.name}---")
            val extension = target.extensions.create("analytics", AnalyticsExtension::class.java)
            target.afterEvaluate {
                val analytics =
                    AnalyticsFactory(extension)
                        .create()

                if (analytics.isNotEmpty()) {
                    target.logger.lifecycle("Analytics amount : ${analytics.size}")
                    target.logger.lifecycle("---Analytics:---")
                    for (adapter in analytics)
                        target.logger.lifecycle(adapter.className)

                    DependencyInstaller(target).install(analytics)
                    TemplateInstaller(target).install(analytics)
                } else {
                    target.logger.lifecycle("Specify adapters into 'analytics' dsl extension block")
                }
            }
        }
    }
}