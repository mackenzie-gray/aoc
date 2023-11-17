package aoc2021

import lib.Direction
import lib.Puzzle
import lib.Vector2
import java.util.Deque

class Day11: Puzzle() {

    override fun reset() {
        TODO("Not yet implemented")
    }


    private fun parseGrid(): MutableMap<Vector2, Int> {
        val grid = mutableMapOf<Vector2, Int>();
        input.reversed().forEachIndexed { y , s ->
            s.forEachIndexed { x, c ->
                val pos = Vector2(x,y)
                grid[pos] = c.digitToInt()
            }
        }
        return grid
    }

    private fun flash(pos: Vector2, grid: MutableMap<Vector2, Int>, flashQueue: ArrayDeque<Vector2>) {
        pos.neighbours.values.forEach {
            if (grid[it] != null) {
                grid[it] = grid[it]!! + 1
                if (grid[it]!! > 9) {
                    flashQueue.add(it)
                }
            }
        }
    }

    private fun step(grid: MutableMap<Vector2, Int>): Int {
        var hasFlashed = mutableListOf<Vector2>();
        var flashQueue = ArrayDeque<Vector2>();
        grid.forEach {
            grid[it.key] = it.value + 1
            if (grid[it.key]!! > 9) {
                flashQueue.addLast(it.key)
            }
        }
        while(!flashQueue.isEmpty()) {
            var pos = flashQueue.removeFirst()
            if (!hasFlashed.contains(pos)) {
                flash(pos, grid, flashQueue)
                hasFlashed.add(pos)
            }
        }
        hasFlashed.forEach {
            grid[it] = 0
        }
        return hasFlashed.size
    }

    private fun printGrid(grid: Map<Vector2, Int>) {
        for (y in 9 downTo 0) {
            for (x in 0 .. 9) {
                print(grid[Vector2(x,y)])
            }
            print("\n");
        }
    }

    override fun partA(): Long {
        val grid = parseGrid()
        var score = 0
        for (i in 1..100) {
            score += step(grid)
        }
        return score.toLong()

    }

    override fun partB(): Long {
        val grid = parseGrid()
        var steps = 0L
        while(step(grid) != 100) {
            steps++
        }
        return steps + 1
    }
}