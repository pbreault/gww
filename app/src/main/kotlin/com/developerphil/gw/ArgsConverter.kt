package com.developerphil.gw

import java.nio.file.FileSystem

fun Array<String>.parseArguments(fileSystem: FileSystem): String {
    val separator = fileSystem.separator

    return this
        .map { convertPathsToGradleCoordinates(it, separator) }
        .joinToString(" ")
}

private fun convertPathsToGradleCoordinates(arg: String, separator: String): String {
    return with(arg.replace(separator, ":")) {
        if (contains(":") && !startsWith(":"))
            ":$this"
        else
            this
    }
}