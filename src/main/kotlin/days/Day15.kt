package days

import java.io.File

class Day15 {
    data class Coordinate(val x: Int, val y: Int) {}

    val grid: MutableSet<Coordinate> = mutableSetOf()

    init {
        val input = File("src/main/resources/input/mini.txt").readText()

    }
}