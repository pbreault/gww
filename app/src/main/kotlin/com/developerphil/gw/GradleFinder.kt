package com.developerphil.gw

import java.nio.file.FileSystem
import java.nio.file.Path
import kotlin.io.path.exists


fun FileSystem.gradlePath(): Path {
    val workingDirectory = getPath("").toAbsolutePath()

    val path = workingDirectory
        .findParentOrNull { path -> path.resolve("gradlew").exists() }
        ?.resolve("gradlew")

    if (path != null) {
        return workingDirectory.relativize(path)
    } else {
        throw GradleNotFoundException(workingDirectory)
    }
}

class GradleNotFoundException(path: Path) : Exception("couldn't find a gradle wrapper under $path")


private fun Path.findParentOrNull(condition: (Path) -> Boolean): Path? {
    return if (condition(this)) {
        this
    } else if (parent != null) {
        parent.findParentOrNull(condition)
    } else {
        null
    }
}
