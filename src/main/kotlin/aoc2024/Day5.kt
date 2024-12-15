package aoc2024

import lib.Puzzle

class Day5 : Puzzle() {
    private val rules = mutableListOf<Pair<String,String>>()
    private val updates = mutableListOf<List<String>>()

    override fun loadInput(fileName: String) {
        super.loadInput(fileName)

        var parseRule = true
        input.forEach { line ->
            if (line.isEmpty()) {
                parseRule = false
            } else if (parseRule) {
                val (a, b) = line.split("|")
                rules.add(Pair(a, b))
            } else {
                updates.add(line.split(","))
            }
        }
    }

    private fun isUpdateCorrect(update: List<String>): Boolean {
        return rules.all { (a, b) ->
            var pos_a = update.indexOf(a)
            var pos_b = update.indexOf(b)
            !(pos_a != -1 && pos_b != -1 && pos_a > pos_b)
        }
    }

    private fun reorderUpdate(update: List<String>): List<String> {
        val newUpdate = update.toMutableList()
        while (!isUpdateCorrect(newUpdate)) {
                rules.forEach { (a, b) ->
                    val pos_a = newUpdate.indexOf(a)
                    val pos_b = newUpdate.indexOf(b)
                    if (pos_a != -1 && pos_b != -1 && pos_a > pos_b) {
                        newUpdate[pos_a] = b
                        newUpdate[pos_b] = a
                    }
                }
        }
        return newUpdate
    }

    override fun partA(): Long {
        return updates.filter { isUpdateCorrect(it) }.fold(0L) { acc, list -> acc + list.getMiddleElement().toLong() }
    }

    override fun partB(): Long {
        val incorrectUpdates = updates.filter { !isUpdateCorrect(it) }
        return incorrectUpdates.fold(0L) { acc, list -> acc + reorderUpdate(list).getMiddleElement().toLong() }
    }

    private fun List<String>.getMiddleElement(): String {
        return this[this.size / 2]
    }
}