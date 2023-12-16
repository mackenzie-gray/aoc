package aoc2023

import lib.Puzzle

class Day8: Puzzle() {
    class Node(var left: String, var right: String)

    override fun partA(): Long {
        val instructions = input[0]
        var steps = 0L
        val nodes = mutableMapOf<String, Node>()

        input.subList(2, input.size).forEach { s ->
            val pattern = Regex("""^(\w+) = \((\w+), (\w+)\)$""")
            val matchResult = pattern.find(s)

            matchResult?.let {
                nodes[it.groupValues[1]] = Node(
                    it.groupValues[2],
                    it.groupValues[3]
                )
            }
        }

        var currentNode = "AAA"
        do {
            val idx = steps.toInt() % instructions.length
            val dir = instructions[idx]
            currentNode = when (dir) {
                'L' -> nodes[currentNode]!!.left
                else -> nodes[currentNode]!!.right
            }
            steps += 1
        } while (currentNode != "ZZZ")

        return steps
    }

    override fun partB(): Long {
        val instructions = input[0]
        var steps = 0L
        val nodes = mutableMapOf<String, Node>()

        input.subList(2, input.size).forEach { s ->
            val pattern = Regex("""^(\w+) = \((\w+), (\w+)\)$""")
            val matchResult = pattern.find(s)

            matchResult?.let {
                nodes[it.groupValues[1]] = Node(
                    it.groupValues[2],
                    it.groupValues[3]
                )
            }
        }

        var currentNodes = nodes.keys.filter { it.last() == 'A' }.toMutableList()
        do {
            val idx = steps.toInt() % instructions.length
            val dir = instructions[idx]
            currentNodes.forEachIndexed { index, s ->
                currentNodes[index] = when (dir) {
                    'L' -> nodes[s]!!.left
                    else -> nodes[s]!!.right
                }
            }
            steps += 1
            if (steps % 1000000 == 0L) {
                print("$steps ")
                println(currentNodes)
            }
        } while (!currentNodes.all { it.last() == 'Z' })

        return steps
    }
}