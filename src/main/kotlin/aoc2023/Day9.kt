package aoc2023

import lib.Puzzle

class Day9: Puzzle() {

    private fun findNextValue(sequence: MutableList<Int>): Int {
        var sqs = mutableListOf(sequence);
        while(!sqs.last().all { it == 0 }) {
            val diffs = sqs.last().windowed(2, 1) {
                it.last() - it.first()
            }
            sqs.add(diffs.toMutableList())
        }

        sqs = sqs.reversed().toMutableList()
        sqs.forEachIndexed { i, list ->
            if (i == 0) {
                list.add(0)
            } else {
                list.add(list.last() + sqs[i-1].last())
            }
        }

        return sqs.last().last()
    }

    private fun findPreviousValue(sequence: MutableList<Int>): Int {
        var sqs = mutableListOf(sequence);
        while(!sqs.last().all { it == 0 }) {
            val diffs = sqs.last().windowed(2, 1) {
                it.last() - it.first()
            }
            sqs.add(diffs.toMutableList())
        }

        sqs = sqs.reversed().toMutableList()
        sqs.forEachIndexed { i, list ->
            if (i == 0) {
                list.add(0, 0)
            } else {
                list.add(0, list.first() - sqs[i-1].first())
            }
        }

        return sqs.last().first()
    }

    override fun partA(): Long {
        return input.sumOf { s ->
            findNextValue(s.split(" ").map { it.toInt() }.toMutableList())
        }.toLong()
    }

    override fun partB(): Long {
        return input.sumOf { s ->
            findPreviousValue(s.split(" ").map { it.toInt() }.toMutableList())
        }.toLong()
    }
}