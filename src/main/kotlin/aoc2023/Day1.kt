package aoc2023

import lib.Puzzle

class Day1: Puzzle() {
    override fun partA(): Long {
        return input.sumOf { line ->
            val digits = line.filter { it.isDigit() }
            "${digits.first()}${digits.last()}".toInt()
        }.toLong()
    }

    override fun partB(): Long {
        val words = listOf(
            "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
        )
        val digits = mutableListOf(
            "1", "2", "3", "4", "5", "6", "7", "8", "9"
        )
        digits.addAll(words)

        return input.sumOf { line ->
            val (_, firstMatch) = line.findAnyOf(digits)!!
            val (_, endMatch) = line.findLastAnyOf(digits)!!

            var num1 = firstMatch
            if (words.contains(firstMatch)) {
                num1 = digits[words.indexOf(firstMatch)]
            }

            var num2 = endMatch
            if (words.contains(endMatch)) {
                num2 = digits[words.indexOf(endMatch)]
            }

            "$num1$num2".toInt()
        }.toLong()
    }

    override fun reset() {
        TODO("Not yet implemented")
    }
}