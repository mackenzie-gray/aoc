package aoc2022

import java.io.File
import java.lang.Integer.max
import java.lang.Integer.min

class Day14 {
    data class Coordinate(val x: Int, val y: Int) {
        fun normalize(factor: Int): Coordinate {
            return Coordinate(x-factor, y)
        }
    }

    val cave: Array<Array<String>>
    val sandStart: Coordinate
    var drops = 0


    fun printCave() {
        cave.forEach { row ->
            println(row.joinToString(" ") { it })
        }
        println("Drops: ${drops}")
    }

    fun dropSandUntilFull() {
        try {
            while(true) {
                val (x, y) = dropSand()
                drops++
                if (x == sandStart.x && y == sandStart.y) {
                    break;
                } else {
                    cave[y][x] = "o"
                }
            }
        } catch (e: Exception) {}
    }

    fun dropSand(): Coordinate {
        var (x, y) = sandStart
        while (true) {
            var nextX = x
            var nextY = y+1
            if (cave[nextY][nextX] == ".") {
                y = nextY
            } else if (cave[nextY][nextX-1] == ".") {
                x = nextX - 1
                y = nextY
            } else if (cave[nextY][nextX+1] == ".") {
                x = nextX + 1
                y = nextY
            } else {
                break
            }
        }

        return Coordinate(x, y)
        cave[y][x] = "o"
    }

    init {
        var minX = 500
        var maxX = 500
        var maxY = 0
        val input = File("src/main/resources/input/14.txt").readText()
        val paths = input.split("\n")
        val pathPoints = paths.map {
            it.split(" -> ").map { pair ->
                pair.split(",")
            }.map { (x, y) ->
                val xI = x.toInt()
                val yI = y.toInt()
                if (xI < minX) minX = xI
                if (xI > maxX) maxX = xI
                if (yI > maxY) maxY = yI
                Coordinate(xI, yI)
            }
        }

        val height = maxY + 3
        val width = height * 2 + 1


        cave = Array(height) {
            Array((width) + 2) { "." }
        }

        // add floor
        cave[cave.count() - 1] = cave.last().map {"#"}.toTypedArray()

        val factor = 500 - (width / 2)

        pathPoints.forEach() {
            it.forEachIndexed() { i, c ->
                if (i != it.count() - 1) {
                    val coord = c.normalize(factor)
                    val nextCoord = it[i+1].normalize(factor)

                    if (coord.x == nextCoord.x) {
                        val y1 = min(coord.y, nextCoord.y)
                        val y2 = max(coord.y, nextCoord.y)
                        for (y in y1..y2) cave[y][coord.x] = "#"
                    } else {
                        val x1 = min(coord.x, nextCoord.x)
                        val x2 = max(coord.x, nextCoord.x)
                        for (x in x1..x2) cave[coord.y][x] = "#"
                    }
                }
            }
        }

        sandStart = Coordinate(500, 0).normalize(factor)
        cave[sandStart.y][sandStart.x] = "+"
    }
}