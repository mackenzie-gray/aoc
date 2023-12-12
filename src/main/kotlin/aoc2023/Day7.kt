package aoc2023

import lib.Puzzle


class Day7: Puzzle() {

    enum class HandType(var strength: Int) {
        FIVE_OF_A_KIND(7),
        FOUR_OF_A_KIND(6),
        FULL_HOUSE(5),
        THREE_OF_A_KIND(4),
        TWO_PAIR(3),
        ONE_PAIR(2),
        HIGH_CARD(1);

        companion object {
            fun getHandType(hand: String, jokerEnabled: Boolean): HandType {
                val counts = mutableMapOf<Char, Int>()
                hand.forEach { counts[it] = counts.getOrDefault(it, 0) + 1 }
                if (jokerEnabled) {
                    val jokerCount = hand.count { it == 'J' }
                    val remaining = hand.filterNot { it == 'J' }
                    val remainingType = getHandType(remaining, false)
                    if (jokerCount >= 4) {
                        return FIVE_OF_A_KIND
                    } else if (jokerCount == 3) {
                        return if (remainingType == ONE_PAIR) {
                            FIVE_OF_A_KIND
                        } else {
                            FOUR_OF_A_KIND
                        }
                    } else if (jokerCount == 2) {
                        return when (remainingType) {
                            THREE_OF_A_KIND -> {
                                FIVE_OF_A_KIND
                            }
                            ONE_PAIR -> {
                                FOUR_OF_A_KIND
                            }
                            else -> {
                                THREE_OF_A_KIND
                            }
                        }
                    } else if (jokerCount == 1) {
                        return when (remainingType) {
                            FOUR_OF_A_KIND -> {
                                FIVE_OF_A_KIND
                            }
                            THREE_OF_A_KIND -> {
                                FOUR_OF_A_KIND
                            }
                            TWO_PAIR -> {
                                FULL_HOUSE
                            }
                            ONE_PAIR -> {
                                THREE_OF_A_KIND
                            }
                            else -> {
                                ONE_PAIR
                            }
                        }
                    }
                }
                if (counts.containsValue(5)) {
                    return FIVE_OF_A_KIND
                } else if (counts.containsValue(4)) {
                    return FOUR_OF_A_KIND
                } else if (counts.containsValue(3) && counts.containsValue(2)) {
                    return FULL_HOUSE
                } else if (counts.containsValue(3)) {
                    return THREE_OF_A_KIND
                } else if (counts.count { it.value == 2 } == 2) {
                    return TWO_PAIR
                } else if (counts.containsValue(2)) {
                    return ONE_PAIR
                } else {
                    return HIGH_CARD
                }
            }
        }
    }


    class Hand(
        private var cards: String,
        var bid: Int,
        jokerEnabled: Boolean = false,
        private val ranks: List<Char> = listOf(
            '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'
        )
    ): Comparable<Hand> {
        private var type = HandType.getHandType(cards, jokerEnabled)

        override fun compareTo(other: Hand): Int {
            if (this.type.strength > other.type.strength) {
                return 1
            } else if (this.type.strength < other.type.strength) {
                return -1
            } else {
                this.cards.forEachIndexed { i, c ->
                    if (c != other.cards[i]) {
                        return if (ranks.indexOf(c) > ranks.indexOf(other.cards[i])) {
                            1
                        } else {
                            -1
                        }
                    }
                }
                return 0
            }
        }
    }

    override fun partA(): Long {
        val hands = mutableListOf<Hand>()
        input.forEach {
            val h = it.split(" ")
            hands.add(Hand(h[0], h[1].toInt()))
        }
        val sorted = hands.sorted()
        return sorted.foldIndexed(0) { i, acc, hand ->
            acc + ((i + 1) * hand.bid)
        }
    }

    override fun partB(): Long {
        val hands = mutableListOf<Hand>()
        val ranks: List<Char> = listOf(
            'J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A'
        )
        input.forEach {
            val h = it.split(" ")
            hands.add(Hand(h[0], h[1].toInt(), true, ranks))
        }
        val sorted = hands.sorted()
        return sorted.foldIndexed(0) { i, acc, hand ->
            acc + ((i + 1) * hand.bid)
        }
    }
}