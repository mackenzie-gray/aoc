package aoc2024

import lib.Puzzle
import kotlin.math.abs

class Day1 : Puzzle() {
    override fun partA(): Long {
        val a: MutableList<Long> = mutableListOf();
        val b: MutableList<Long> = mutableListOf();

        input.map {
            val pair = it.split("   ")
            a.add(pair[0].toLong())
            b.add(pair[1].toLong())
        }

        a.sort()
        b.sort()

        return a.foldIndexed(0L) { index, acc, value ->
            acc + abs(value - b[index])
        }
    }

    override fun partB(): Long {
        val a: MutableList<Long> = mutableListOf();
        val b: MutableList<Long> = mutableListOf();

        input.map {
            val pair = it.split("   ")
            a.add(pair[0].toLong())
            b.add(pair[1].toLong())
        }

        return a.fold(0L) { acc, value ->
            acc + value * b.count() { it == value }
        }
    }
}