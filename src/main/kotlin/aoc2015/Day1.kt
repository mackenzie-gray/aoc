package aoc2015

import lib.Puzzle

class Day1: Puzzle() {
    override fun partA(): Long {
        return input[0].fold(0) { acc, c ->
            if (c == '(') acc + 1 else acc - 1
        }
    }

    override fun partB(): Long {
        return input[0].foldIndexed(0) { i, acc, c ->
            if (acc < 0) return i.toLong()
            if (c == '(') acc + 1 else acc - 1
        }
    }
}