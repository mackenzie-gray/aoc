package aoc2023

import lib.Puzzle

class Day8: Puzzle() {
    class Node(var left: String, var right: String)
    override fun partA(): Long {
        val instructions = input[0]
        var steps = 0
        var nodes = mutableMapOf<String, Node>()
        TODO("Not yet implemented")
    }

    override fun partB(): Long {
        TODO("Not yet implemented")
    }
}