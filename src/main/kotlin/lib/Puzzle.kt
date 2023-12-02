package lib

import java.io.File

abstract class Puzzle() {
    var input: List<String> = listOf()
    abstract fun partA(): Long
    abstract fun partB(): Long
    fun loadInput(fileName: String) {
        input = File(fileName).readLines()
    }
}