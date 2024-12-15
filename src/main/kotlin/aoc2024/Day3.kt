package aoc2024

import lib.Puzzle
import java.util.regex.Pattern
import kotlin.streams.toList

class Day3 : Puzzle() {


    override fun partA(): Long {
        val regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)"
        val pattern = Pattern.compile(regex)
        return input.fold(0L) { acc, s ->
            val total = pattern.matcher(s).results().map { match ->
                match.group(1).toLong() * match.group(2).toLong()
            }.toList().sum()
            acc + total
        }
    }

    override fun partB(): Long {
        val regex = "(do(n't)?)|(mul\\((\\d{1,3}),(\\d{1,3})\\))"
        val pattern = Pattern.compile(regex)
        var active = true
        return input.fold(0) { acc, s ->
            val total = pattern.matcher(s).results().map { match ->
                if (active && match.group()[0] == 'm') {
                    match.group(4).toLong() * match.group(5).toLong()
                } else if (match.group() == "don't") {
                    active = false
                    0
                } else if (match.group() == "do") {
                    active = true
                    0
                } else {
                    0
                }
            }.toList().sum()
            acc + total
        }
    }
}