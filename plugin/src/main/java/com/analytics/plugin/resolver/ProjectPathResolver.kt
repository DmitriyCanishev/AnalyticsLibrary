package com.analytics.plugin.resolver

import org.gradle.api.Project
import java.io.File

class ProjectPathResolver(private val target: Project) {

    fun getTargetDirectory(namespace: String): File {
        val directory = getDirectory(namespace)

        if (!directory.exists()) {
            directory.mkdirs()
        }

        return directory
    }

    private fun getDirectory(namespace: String): File {
        val sourceSets = kotlinOrJavaDirectory()
        val packagePath = namespace.replace(".", File.separator)

        return File(sourceSets, "$packagePath/analytics")
    }

    private fun kotlinOrJavaDirectory(): File {
        val kotlinPath = target.file("src/main/kotlin")

        if (kotlinPath.exists()) {
            return kotlinPath
        }

        return target.file("src/main/java")
    }
}
