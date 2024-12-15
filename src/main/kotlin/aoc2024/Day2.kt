package aoc2024

import lib.Puzzle

class Day2 : Puzzle() {
    private fun isReportSafe(values: List<Int>): Boolean {
        val isAscending = values[0] - values[1] <= 0
        return values.windowed(2, 1).all { (a, b) ->
            var distance = a - b
            if (isAscending) {
                distance *= -1
            }
            (1 .. 3).contains(distance)
        }
    }

    override fun partA(): Long {
        return input.count {
            val values = it.split(" ").map { i -> i.toInt() }
            isReportSafe(values)
        }.toLong()
    }

    override fun partB(): Long {
        return input.count { content ->
            val values = content.split(" ").map { it.toInt() }
            values.indices.any { i ->
                val subReport = values.toMutableList()
                subReport.removeAt(i)
                isReportSafe(subReport)
            }
        }.toLong()
    }
}