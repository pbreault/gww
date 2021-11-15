/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.developerphil.gww

import java.nio.file.FileSystem
import java.nio.file.FileSystems

class App(private val args: Array<String>, private val fileSystem: FileSystem) {
    fun run(): String {
        val executableLocation = fileSystem.gradlePath()
        val arguments = args.parseArguments(fileSystem)

        return "$executableLocation $arguments"
    }
}

fun main(args: Array<String>) {
    println(
        App(
            args,
            FileSystems.getDefault()
        ).run()
    )
}
