package aoc2024

import lib.Direction
import lib.Puzzle
import lib.Vector2

class Day6 : Puzzle() {
    val map: MutableMap<Vector2, String> = mutableMapOf()

    override fun loadInput(fileName: String) {
        super.loadInput(fileName)

        input.reversed().forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                map[Vector2(x, y)] = c.toString()
            }
        }
    }

    private fun traverse(start: Vector2, obstacle: Vector2?): List<Pair<Vector2, Direction>> {
        val path = mutableListOf(start to Direction.NORTH)

        var currentPos = start
        var currentDir = Direction.NORTH

        while(map[currentPos] != null) {
            path.add(currentPos to currentDir)
            val nextPos = currentPos.neighbours[currentDir]
            if (map[nextPos] == "#" || (obstacle != null && nextPos == obstacle)) {
                currentDir = currentDir.turnRightSimple()
            } else {
                currentPos = nextPos!!
                if (path.contains(currentPos to currentDir)) {
                    throw Exception("Loop detected")
                }
            }
        }
        return path
    }

    override fun partA(): Long {
        var start = map.entries.find{ it.value == "^" }?.key ?: Vector2(0,0)
        var path = traverse(start, null)

        return path.map { it.first }.toSet().size.toLong()
    }

    override fun partB(): Long {
        var start = map.entries.find{ it.value == "^" }?.key ?: Vector2(0,0)
        var path = traverse(start, null)

        var validObstacleSpots = 0

        path.filter { it.first != start }.map { it.first }.toSet().forEach {
            try {
                traverse(start, it)
            } catch (exception: Exception) {
                validObstacleSpots++
            }

        }

        return validObstacleSpots.toLong();
    }
}