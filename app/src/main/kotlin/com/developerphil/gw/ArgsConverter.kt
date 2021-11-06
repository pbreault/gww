package com.developerphil.gw

import java.nio.file.FileSystem

fun Array<String>.parseArguments(fileSystem: FileSystem): String {
    val separator = fileSystem.separator

    return this
        .map { convertPathsToGradleCoordinates(it, separator) }
        .joinToString(" ")
}

private fun convertPathsToGradleCoordinates(arg: String, separator: String): String {
    if (arg.startsWith("\"") && arg.endsWith("\"")) {
        return arg
    }

    val taskSeparator = "$separator$separator"
    if (arg.contains(taskSeparator)) {
        var project = arg.substringBeforeLast(":")
        var tasks = arg.substringAfterLast(":")

        return tasks.split(taskSeparator)
            .map { task -> convertPathsToGradleCoordinates("$project:$task", separator) }
            .joinToString(" ")
    }

    return with(arg.replace(separator, ":")) {
        if (contains(":") && !startsWith(":"))
            ":$this"
        else
            this
    }
}