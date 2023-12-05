package aoc2023

import lib.Puzzle


class Day5: Puzzle() {

    private fun findLowestLocation(seeds: List<Long>): Long {
        var transition_ranges = mutableMapOf<Long, LongRange>();

        var items = seeds.toMutableList()
        for (line in input.subList(2, input.lastIndex)) {
            if (line == "") {
                items = items.mapIndexed { i, item ->
                    var transition = transition_ranges.filter {
                        it.value.contains(item)
                    }
                    if (transition.isNotEmpty()) {
                        transition.keys.first() + item
                    } else {
                        item
                    }
                }.toMutableList()
            } else if (line.contains("map")) {
                transition_ranges = mutableMapOf();
            } else {
                var vals = line.split(" ").map { it.toLong() }
                val diff = vals[0] - vals[1]
                val range = vals[1] until vals[1]+vals[2]
                transition_ranges[diff] = range
            }
        }
        items = items.mapIndexed { i, item ->
            var transition = transition_ranges.filter {
                it.value.contains(item)
            }
            if (transition.isNotEmpty()) {
                transition.keys.first() + item
            } else {
                item
            }
        }.toMutableList()

        return items.min();
    }

    override fun partA(): Long {
        val original_seeds = input[0].split(": ")[1].split(" ").map{ it.toLong() }

        return findLowestLocation(original_seeds)
    }

    override fun partB(): Long {
        val original_seeds = mutableListOf<Long>();
        input[0].split(": ")[1].split(" ").windowed(2,2) {
            for(i in it[0].toLong() .. it[0].toLong()+it[1].toLong()) {
                original_seeds.add(i)
            }
        }
        return findLowestLocation(original_seeds)
    }
}