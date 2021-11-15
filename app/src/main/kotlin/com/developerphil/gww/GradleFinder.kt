package com.developerphil.gww

import java.nio.file.FileSystem
import java.nio.file.Path
import kotlin.io.path.exists

private const val GRADLEW_EXECUTABLE: String = "gradlew"

fun FileSystem.gradlePath(): Path {
    val workingDirectory = getPath("").toAbsolutePath()

    val path = workingDirectory
        .findParentOrNull { path -> path.resolve(GRADLEW_EXECUTABLE).exists() }
        ?.resolve(GRADLEW_EXECUTABLE)

    if (path != null) {
        return workingDirectory.relativize(path).toUnixExecutable()
    } else {
        throw GradleNotFoundException(workingDirectory)
    }
}


fun Path.toUnixExecutable(): Path {
    return if (toString() == GRADLEW_EXECUTABLE)
        resolveSibling("./$GRADLEW_EXECUTABLE")
    else
        this
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
