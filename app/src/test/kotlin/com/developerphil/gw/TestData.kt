package com.developerphil.gw

import com.google.common.jimfs.Configuration
import com.google.common.jimfs.Jimfs
import java.nio.file.Files

object TestData {

    /**
     * Structure:
     * /
     * ├── project
     * │   ├── gradlew
     * │   ├── project_a
     * │   │   ├── feature
     * │   │   │   └── login
     * │   │   └── gradlew
     * │   └── project_b
     * └── tmp
     *     └── logs
     */
    fun unixFileSystem(workingDirectory: String = "/project") = Jimfs.newFileSystem(
        Configuration.unix().toBuilder()
            .setWorkingDirectory(workingDirectory)
            .build()
    )
        .apply {
            Files.createDirectories(getPath("/project/project_a/feature/login"))
            Files.createFile(getPath("/project/gradlew"))
            Files.createFile(getPath("/project/project_a/gradlew"))

            Files.createDirectories(getPath("/project/project_b"))
            Files.createDirectories(getPath("/tmp/logs"))
        }

}