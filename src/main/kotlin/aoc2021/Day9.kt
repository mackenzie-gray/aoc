package aoc2021

import lib.Direction
import lib.Puzzle
import lib.Vector2

class Day9: Puzzle() {
    var grid: MutableMap<Vector2, Long> = mutableMapOf();
    override fun reset() {
        TODO("Not yet implemented")
    }

    fun isLowPoint(pos: Vector2): Boolean {
        var n = arrayOf(
            grid[pos.neighbours[Direction.NORTH]],
            grid[pos.neighbours[Direction.EAST]],
            grid[pos.neighbours[Direction.SOUTH]],
            grid[pos.neighbours[Direction.WEST]],
        ).filterNotNull().filter {
            it > grid[pos]!!
        }
        return n.isEmpty()
    }

    override fun partA(): Long {
        input.reversed().forEachIndexed { y, it ->
            it.forEachIndexed { x, c ->
                val location = Vector2(x,y);
                grid[location] = c.code.toLong()
            }
        }
        var lowPoints = mutableListOf<Long>(0)
        grid.forEach { it ->
            if (isLowPoint(it.key)) {
                lowPoints.add(it.value)
        }}

        return lowPoints.reduce { acc, i ->
            acc + i + 1
        }
    }
    override fun partB(): Long {
        TODO("Not yet implemented")
    }
}