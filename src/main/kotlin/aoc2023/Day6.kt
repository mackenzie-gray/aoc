package aoc2023

import lib.Puzzle

class Day6: Puzzle() {

    fun getGameResultPossibilities(time: Long): List<Long> {
        var results = mutableListOf<Long>()
        for (i in 0..time) {
            results.add((time - i) * i)
        }
        return results
    }

    override fun partA(): Long {
        val times = input[0].removePrefix("Time:")
            .split(" ")
            .filterNot { it == "" }
            .map { it.toLong() }
        val scores = input[1].removePrefix("Distance:")
            .split(" ")
            .filterNot { it == "" }
            .map { it.toLong() }

        val wins = times.mapIndexed { i, time ->
            getGameResultPossibilities(time).count { it > scores[i] }
        }

        var score = wins.fold(1L) { acc, it ->
            acc * it
        }

        return score
    }

    fun findLeftBound(range: Pair<Long,Long>, time: Long, score: Long): Long {
        if (range.second - range.first == 1L) {
            if ((time - range.first) * range.first <= score) {
                return range.second
            } else {
                return range.first
            }
        }
        var midTime = ((range.second - range.first) / 2) + range.first
        var pScore = (time - midTime) * midTime;
        if (pScore > score) {
            return findLeftBound(Pair(range.first,midTime), time, score)
        } else {
            return findLeftBound(Pair(midTime,range.second), time, score)
        }
    }

    fun findRightBound(range: Pair<Long,Long>, time: Long, score: Long): Long {
        if (range.second - range.first == 1L) {
            if ((time - range.first) * range.first >= score) {
                return range.second
            } else {
                return range.first
            }
        }
        var midTime = ((range.second - range.first) / 2) + range.first
        var pScore = (time - midTime) * midTime;
        if (pScore <= score) {
            return findRightBound(Pair(range.first,midTime), time, score)
        } else {
            return findRightBound(Pair(midTime, range.second), time, score)
        }
    }


    override fun partB(): Long {
        val time = input[0].removePrefix("Time:")
            .filterNot { it == ' ' }
            .toLong()
        val score = input[1].removePrefix("Distance:")
            .filterNot { it == ' ' }
            .toLong()

        val range =
            findLeftBound(Pair(0,time), time, score)..
            findRightBound(Pair(0,time), time, score)
        return range.count().toLong() - 1
    }
}