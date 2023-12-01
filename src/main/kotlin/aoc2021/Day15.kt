package aoc2021

import lib.Puzzle
import lib.Vector2

class Day15: Puzzle() {

    class Node(var weight: Int, var vec: Vector2) {
        var visited = false
        val neighbours: MutableList<Node> = mutableListOf()
        var predecessor: Node? = null
    }

    override fun reset() {
        TODO("Not yet implemented")
    }

    override fun partA(): Long {
        var graph = mutableMapOf<Vector2, Node>();
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                val w = c.digitToInt()
                val pos = Vector2(x,y)
                graph[pos] = Node(w, pos)
            }
        }

        graph.forEach { node ->
            node.key.simpleNeighbours.forEach {
                graph[it.value]?.let { nn ->
                    node.value.neighbours.add(nn)
                }
            }
        }

        val mx = graph.keys.maxBy { it.x }.x
        val my = graph.keys.maxBy { it.y }.y

        var pathDistance = findShortestPath(graph, graph[Vector2(0f,0f)]!!, graph[Vector2(mx,my)]!!)
        return pathDistance
    }

    override fun partB(): Long {
        var graph = mutableMapOf<Vector2, Node>();
        for(tileY in 0..4) {
            for(tileX in 0 .. 4) {
                input.forEachIndexed { y, line ->
                    line.forEachIndexed { x, c ->
                        var w = (c.digitToInt() + tileX + tileY)
                        if (w > 9) w = w % 10 + 1
                        val pos = Vector2(x + (tileX * 10), y + (tileY * 10))
                        graph[pos] = Node(w, pos)
                    }
                }
            }
        }

        graph.forEach { node ->
            node.key.simpleNeighbours.forEach {
                graph[it.value]?.let { nn ->
                    node.value.neighbours.add(nn)
                }
            }
        }

        val mx = graph.keys.maxBy { it.x }.x
        val my = graph.keys.maxBy { it.y }.y

        var pathDistance = findShortestPath(graph, graph[Vector2(0f,0f)]!!, graph[Vector2(mx,my)]!!)
        return pathDistance
    }

    fun findShortestPath(graph: Map<Vector2, Node>, from: Node, to: Node): Long {
        class Dist(var node: Node, var dist: Int): Comparable<Dist> {
            override operator fun compareTo(o: Dist): Int {
                return if (this.dist == o.dist) {
                    0
                } else if (this.dist < o.dist){
                    -1
                } else {
                    1
                }
            }
        }

        var distances = mutableMapOf<Vector2, Int>()
        var tentativeDistances = mutableListOf<Dist>()
        graph.forEach {
            if (from == it.value) {
                distances[it.key] = 0
            } else {
                distances[it.key] = Int.MAX_VALUE
            }
            tentativeDistances.add(Dist(it.value, distances[it.key]!!))
        }

        run loop@{
            while(true) {
                val current = tentativeDistances.find { !it.node.visited }
                if (current == null || current.dist == Int.MAX_VALUE || current.node == to) { return@loop }
                current.node.neighbours.forEach { node ->
                    val t = tentativeDistances.find { it.node == node }
                    val newDist = current.dist + t!!.node.weight
                    if (newDist < t.dist) {
                        t.dist = newDist
                        t.node.predecessor = current.node
                    }
                }
                tentativeDistances = tentativeDistances.sorted().toMutableList()
                current.node.visited = true
            }
        }

        return tentativeDistances.find { it.node == to }!!.dist.toLong()
    }

}