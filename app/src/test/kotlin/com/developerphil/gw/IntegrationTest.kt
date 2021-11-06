/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.developerphil.gw

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.developerphil.gw.TestData.unixFileSystem
import org.junit.Test

class IntegrationTest {

    @Test
    fun `can run a simple command`() {
        val app = App(arrayOf("clean", "assemble"), unixFileSystem(workingDirectory = "/project"))

        assertThat(app.run()).isEqualTo("./gradlew clean assemble")
    }

    @Test
    fun `can run a simple command from a different folder`() {
        val app =
            App(arrayOf("clean", "assemble"), unixFileSystem(workingDirectory = "/project/project_a/feature/login"))

        assertThat(app.run()).isEqualTo("../../gradlew clean assemble")
    }


    @Test
    fun `can run a commands with replacements from the root folder`() {
        val app = App(
            arrayOf("feature/login:clean", "feature/login:assemble"),
            unixFileSystem(workingDirectory = "/project/project_a")
        )

        assertThat(app.run()).isEqualTo("./gradlew :feature:login:clean :feature:login:assemble")
    }

    @Test
    fun `can combine commands on a single project`() {
        val app = App(arrayOf("feature/login:clean//assemble"), unixFileSystem(workingDirectory = "/project/project_a"))

        assertThat(app.run()).isEqualTo("./gradlew :feature:login:clean :feature:login:assemble")
    }


    @Test
    fun `keep file paths as is for include-build`() {
        val app =
            App(
                arrayOf("clean", "assemble", "--include-build", "\"/project/projectb\""),
                unixFileSystem(workingDirectory = "/project/project_a/feature/login")
            )

        assertThat(app.run()).isEqualTo("../../gradlew clean assemble --include-build \"/project/projectb\"")
    }
}

