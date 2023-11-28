package aoc2021

import lib.Puzzle

class Day14: Puzzle() {

    override fun reset() {
        TODO("Not yet implemented")
    }

    fun expand(s: String, rules: Map<String, String>): String {
        var insert = "";
        s.windowed(2, 1) {
            insert += if (rules[it] != null) {
                rules[it]
            } else {
                " "
            }
        }

        var new = ""
        for(i in s.indices) {
            new += s[i]
            if (i < insert.length && insert[i] != ' ') {
                new += insert[i]
            }
        }

        return new;
    }

    fun parseInput(): Pair<String, Map<String,String>> {
        var s = input[0];
        var rules = mutableMapOf<String, String>();
        input.subList(2, input.size).forEach {
            var rule = it.split(" -> ")
            rules[rule[0]] = rule[1];
        }

        return Pair(s, rules);
    }

    fun runNaive(count: Int): Long {
        var (s, rules) = parseInput();

        for (i in 1..count) {
            s = expand(s, rules)
        }

        val count = mutableMapOf<Char, Long>()
        var elements = s.toSet()
        elements.forEach { c ->
            count[c] = s.count(c)
        }

        var max = count.maxBy { it.value }
        var min = count.minBy { it.value }


        return (max.value - min.value)
    }

    fun runSmart(count: Int): Map<Char, Long>{
        val (s, rules) = parseInput();
        val elements = mutableMapOf<Char, Long>();
        var counts = mutableMapOf<String, Long>();

        s.windowed(2, 1) {
            counts[it.toString()] = counts.getOrDefault(it.toString(), 0) + 1
        }
        s.forEach {
            elements[it] = elements.getOrDefault(it, 0) + 1
        }

        for (i in 1..count) {
            counts = expandPairs(counts, rules, elements)
        }

        return elements;
    }

    fun expandPairs(counts: MutableMap<String, Long>, rules: Map<String, String>, elements: MutableMap<Char, Long>): MutableMap<String, Long> {
        val r = mutableMapOf<String, Long>();

        counts.forEach {
            val char = rules[it.key];
            if (char != null) {
                elements[char[0]] = elements.getOrDefault(char[0], 0) + it.value
                val first = it.key[0] + char
                val second = char + it.key[1]
                r[first] = r.getOrDefault(first, 0) + it.value
                r[second] = r.getOrDefault(second, 0) + it.value
            } else {
                r[it.key] = r.getOrDefault(it.key, 0) + it.value
            }
        }

        return r
    }

    private fun getResult(elements: Map<Char, Long>): Long {
        val max = elements.maxBy { it.value }
        val min = elements.minBy { it.value }
        return max.value - min.value
    }


    override fun partA(): Long {
        val r = runSmart(10)
        return getResult(r);
    }

    override fun partB(): Long {
        val r = runSmart(40)
        return getResult(r);
    }

    fun String.count(c: Char): Long {
        var count: Long = 0;
        this.forEach {
            if (it == c) {
                count++
            }
        }
        return count
    }
}