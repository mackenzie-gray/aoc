package aoc2021

import lib.Puzzle

class Day12: Puzzle() {

    override fun reset() {
        TODO("Not yet implemented")
    }

    private fun buildGraph(): Map<String, List<String>> {
        var graph = mutableMapOf<String, MutableList<String>>()
        input.forEach {
            val tunnel = it.split("-")
            val left = tunnel[0]
            val right = tunnel[1]

            if (graph[left]?.add(right) == null) {
                graph[left] = mutableListOf(right)
            }

            if (graph[right]?.add(left) == null) {
                graph[right] = mutableListOf(left)
            }
        }
        return graph
    }

    private fun findPaths(path: List<String>, graph: Map<String, List<String>>, canDouble: Boolean): List<List<String>> {
        val neighbours = graph[path.last()]
        val paths: MutableList<List<String>> = mutableListOf()
        neighbours!!.mapNotNull {
            if (it == "end") {
                val complete = path.toMutableList()
                complete.add(it)
                listOf(complete)
            } else {
                val isSmall = it == it.lowercase()
                if (isSmall && path.contains(it)) {
                    if (canDouble && it != "start") {
                        val partial = path.toMutableList()
                        partial.add(it)
                        findPaths(partial, graph, false)
                    } else {
                        null
                    }
                } else {
                    val partial = path.toMutableList()
                    partial.add(it)
                    findPaths(partial, graph, canDouble)
                }
            }
        }.forEach {
            paths.addAll(it)
        }
        return paths
    }

    override fun partA(): Long {
        var graph = buildGraph()
        var paths = findPaths(listOf("start"), graph, false)
        return paths.size.toLong();
    }

    override fun partB(): Long {
        var graph = buildGraph()
        var paths = findPaths(listOf("start"), graph, true)
        return paths.size.toLong()
    }
}