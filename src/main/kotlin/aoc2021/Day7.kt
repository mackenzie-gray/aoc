package aoc2021

import lib.Puzzle
import kotlin.math.abs

class Day7: Puzzle() {
    override fun reset () {
    }

    private fun getPositionCost(position: Long, input: List<Long>): Long {
        val list: MutableList<Long> = mutableListOf(0)
        list.addAll(input)
        return list.reduce { acc, l ->
            acc + abs(l - position)
        }
    }

    private fun getFullCost(position: Long, input: List<Long>): Long {
        val list: MutableList<Long> = mutableListOf(0)
        list.addAll(input)
        return list.reduce { acc, l ->
            val stepCount = abs(l - position)
            var cost = 0
            if (stepCount != 0L) {
                cost = IntRange(1, stepCount.toInt()).reduce { r, i -> r + i }
            }
            acc + cost
        }
    }

    override fun partA(): Long {
        val vals = input[0].split(",").map {
            it.toLong()
        }

        var cheapestCost = getPositionCost(vals.min(), vals)
        for (i in vals.min() .. vals.max()) {
            val cost = getPositionCost(i, vals)
            if (cost < cheapestCost) {
                cheapestCost = cost
            }
        }
        return cheapestCost
    }

    override fun partB(): Long {
        val values = input[0].split(",").map {
            it.toLong()
        }

        var cheapestCost = getFullCost(values.min(), values)
        for (i in values.min() .. values.max()) {
            val cost = getFullCost(i, values)
            if (cost < cheapestCost) {
                cheapestCost = cost
            }
        }
        return cheapestCost
    }
}