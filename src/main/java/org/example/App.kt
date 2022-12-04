package org.example

import java.io.File

/**
 * Hello world!
 *
 */
object App {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Hello World!")
        println(File("./apks").listFiles().map { it.absolutePath })
    }
}