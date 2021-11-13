package com.developerphil.gw

import java.nio.file.FileSystem

fun Array<String>.parseArguments(fileSystem: FileSystem): String {
    val separator = fileSystem.separator

    return this.joinToString(separator = " ") { convertPathsToGradleCoordinates(it, separator) }
}

private fun convertPathsToGradleCoordinates(arg: String, separator: String): String {
    if (arg.startsWith("\"") && arg.endsWith("\"")) {
        return arg
    }

    val taskSeparator = "$separator$separator"
    if (arg.contains(taskSeparator)) {

        val project = if (arg.contains(":")) {
            arg.substringBeforeLast(":") + ":"
        } else {
            ""
        }

        val tasks = arg.substringAfterLast(":")

        return tasks.split(taskSeparator)
            .joinToString(separator = " ") { task -> convertPathsToGradleCoordinates("$project$task", separator) }
    }

    return with(arg.replace(separator, ":")) {
        if (contains(":") && !startsWith(":"))
            ":$this"
        else
            this
    }
}