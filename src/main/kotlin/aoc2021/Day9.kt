package aoc2021

import lib.Direction
import lib.Puzzle
import lib.Vector2

class Day9: Puzzle() {
    var grid: MutableMap<Vector2, Long> = mutableMapOf();
    override fun reset() {
        TODO("Not yet implemented")
    }

    private fun isLowPoint(pos: Vector2): Boolean {
        val n = arrayOf(
            grid[pos.neighbours[Direction.NORTH]],
            grid[pos.neighbours[Direction.EAST]],
            grid[pos.neighbours[Direction.SOUTH]],
            grid[pos.neighbours[Direction.WEST]],
        ).filterNotNull().filter {
            it <= grid[pos]!!
        }
        return n.isEmpty()
    }

    private fun buildGrid() {
        input.reversed().forEachIndexed { y, it ->
            it.forEachIndexed { x, c ->
                val location = Vector2(x,y);
                grid[location] = c.toString().toLong()
            }
        }
    }

    private fun getLowPoints(): MutableList<Vector2> {
        val lowPoints = mutableListOf<Vector2>()
        grid.forEach { it ->
            if (isLowPoint(it.key)) {
                lowPoints.add(it.key)
            }
        }
        return lowPoints
    }

    private fun findBasinNeighbours(pos: Vector2): List<Vector2> {
        val n = arrayOf(
            pos.neighbours[Direction.NORTH],
            pos.neighbours[Direction.EAST],
            pos.neighbours[Direction.SOUTH],
            pos.neighbours[Direction.WEST],
        ).filter {
            grid[it] != null && grid[it]!! != 9L
        }

        val p = n.map {
            findBasinNeighbours(it!!)
        }.flatten()

        val basin = mutableListOf(pos)
        basin.addAll(p)

        return basin
    }

    override fun partA(): Long {
        buildGrid()
        val lowPoints = getLowPoints().map {
            grid[it]!!
        }

        return lowPoints.fold(0) { acc, i ->
            acc + i + 1
        }
    }
    override fun partB(): Long {
        buildGrid()
        val lowPoints = getLowPoints()

        val basins = lowPoints.map {
            findBasinNeighbours(it)
        }


        return 0L;
    }
}