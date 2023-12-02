package aoc2021

import lib.Puzzle
import kotlin.math.abs

class Day8: Puzzle() {
    override fun partA(): Long {
        var count = 0L;
        input.forEach() {
            it.split("|")[1].split(" ").forEach { digit ->
                if (digit.length == 2 || digit.length == 3 || digit.length == 4 || digit.length == 7) {
                    count += 1
                }
            }
        }
        return count
    }

    override fun partB(): Long {
        return 0
    }
}