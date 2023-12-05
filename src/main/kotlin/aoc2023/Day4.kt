package aoc2023

import lib.Puzzle
import kotlin.math.pow

class Day4: Puzzle() {
    private val winCounts: Map<Int,Int>
        get() {
            val res = mutableMapOf<Int, Int>()
            input.forEachIndexed { i, line ->
                val lists = line.split("|", ": ")
                val winners = lists[1].split(" ").filterNot { it.isEmpty() }.map {
                    it.toInt()
                }.toSet()
                val nums = lists[2].split(" ").filterNot { it.isEmpty() }.map {
                    it.toInt()
                }.toSet()

                val wins = winners.intersect(nums).size.toDouble()
                res[i] = wins.toInt()
            }
            return res
        }

    override fun partA(): Long {
        return winCounts.values.sumOf{
            2.0.pow((it.toDouble() - 1)).toLong()
        }
    }

    override fun partB(): Long {
        var totalCards = 0
        var cardCopies = mutableMapOf<Int, Int>();
        input.forEachIndexed() { i, _ -> cardCopies[i] = 1 }


        while(cardCopies.isNotEmpty()) {
            totalCards += cardCopies.values.sumOf { it }
            cardCopies = getCardCopiesFromWinners(cardCopies)
        }

        return totalCards.toLong()
    }

    private fun getCardCopiesFromWinners(winners: Map<Int, Int>): MutableMap<Int, Int> {
        val copies = mutableMapOf<Int, Int>();
        for ((index, numCards) in winners) {
            for (j in 1..winCounts[index]!!) {
                copies[index+j] = copies.getOrDefault(index+j, 0) + numCards
            }
        }
        return copies
    }
}