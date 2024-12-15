package aoc2024

import lib.Direction
import lib.Puzzle
import lib.Vector2

class Day4 : Puzzle() {
    private val grid: MutableMap<Vector2, Char> = mutableMapOf()

    override fun loadInput(fileName: String) {
        super.loadInput(fileName)

        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                grid[Vector2(x, y)] = c
            }
        }
    }

    override fun partA(): Long {
        return grid.toList().fold(0) { acc, (pos, c) ->
            if (c == 'X') {
                val words = pos.neighbours.map { (direction, vector) ->
                    var word = "X"
                    var current = vector
                    for (i in 1..3) {
                        val next = grid[current] ?: break
                        word += next
                        current = current.neighbours[direction]!!
                    }
                    word
                }
                acc + words.count() { it == "XMAS" }
            } else {
                acc
            }
        }
    }

    override fun partB(): Long {
        return grid.toList().fold(0) { acc, (pos, c) ->
            if (c == 'A') {
                val word1 = "${grid[pos.neighbours[Direction.NORTHWEST]]}$c${grid[pos.neighbours[Direction.SOUTHEAST]]}"
                val word2 = "${grid[pos.neighbours[Direction.NORTHEAST]]}$c${grid[pos.neighbours[Direction.SOUTHWEST]]}"
                if (listOf(word1, word2).all { it == "MAS" || it == "SAM" }) {
                    acc + 1
                } else acc
            } else {
                acc
            }
        }
    }
}