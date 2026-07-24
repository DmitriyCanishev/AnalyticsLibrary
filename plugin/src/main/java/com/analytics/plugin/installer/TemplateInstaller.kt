package com.analytics.plugin.installer

import com.analytics.plugin.generator.AnalyticsServiceGenerator
import com.analytics.plugin.model.AnalyticsDescriptor
import com.analytics.plugin.resolver.ProjectPathResolver
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project
import java.io.File

class TemplateInstaller(private val target: Project) {
    fun install(analytics: List<AnalyticsDescriptor>) {
        target.logger.lifecycle("---Creating AnalyticsHandler---")
        val namespace = getNamespace()
        val packageName = "$namespace.analytics"
        val directory = ProjectPathResolver(target).getTargetDirectory(namespace)

        val targetFile = File(directory, "AnalyticsServiceHandler.kt")

        val isExist = targetFile.exists()
        val analyticsGenerator = AnalyticsServiceGenerator().generate(
            template = getTemplate(),
            packageName = packageName,
            analytics = analytics
        )

        targetFile.writeText(analyticsGenerator)

        if (isExist) {
            target.logger.lifecycle("AnalyticsHandler was updated")
            return
        }

        target.logger.lifecycle("AnalyticsHandler created: ${targetFile.path}")
        target.logger.lifecycle("--AnalyticsHandler template installed--")
    }

    private fun getNamespace(): String {
        val project = target.extensions.getByType(ApplicationExtension::class.java)

        return requireNotNull(project.namespace) {
            "Namespace is not specified"
        }
    }

    private fun getTemplate(): String {
        return javaClass
            .classLoader
            .getResourceAsStream("templates/AnalyticsServiceHandler.kt")
            ?.bufferedReader()
            ?.readText()
            ?: error("AnalyticsServiceHandler.kt template not found")
    }
}