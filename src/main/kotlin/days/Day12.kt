package days

import java.io.File

class Day12 {
    data class Coordinate(val x: Int, val y: Int)
    class Node(val x: Int, val y: Int, val value: Char, val depth: Int) {
        var parent: Node? = null
        val children: MutableList<Node> = mutableListOf()

        fun hasParent(x: Int, y: Int): Boolean {
            return if (parent == null) {
                false
            } else if (x == parent!!.x && y == parent!!.y) {
                true
            } else {
                parent!!.hasParent(x, y)
            }
        }
    }

    var graph: Node? = null
    var grid: ArrayList<MutableList<Char>> = arrayListOf()
    var shortestPathLength: Int = -1
    var shortestPathNode: Node? = null
    val searchQueue: ArrayDeque<Node> = ArrayDeque();
    val visitedNodes: MutableList<Node> = ArrayList<Node>();

    private fun findStartPosition(): Coordinate? {
        grid.forEachIndexed() { index, row ->
            val pos = row.indexOf('E')
            if (pos > -1) {
                return Coordinate(pos, index)
            }
        }
        return null
    }

    enum class Direction {
        U, D, L, R
    }

    private fun getMove(dir: Direction, coordinate: Coordinate): Coordinate {
        return when(dir) {
            Direction.U -> Coordinate(coordinate.x, coordinate.y - 1)
            Direction.L -> Coordinate(coordinate.x - 1, coordinate.y)
            Direction.D -> Coordinate(coordinate.x, coordinate.y + 1)
            Direction.R -> Coordinate(coordinate.x + 1, coordinate.y)
        }
    }

    fun hasVisitedNode(coordinate: Coordinate): Boolean {
        return visitedNodes.find {
            coordinate.x == it.x && coordinate.y == it.y
        } != null
    }

    fun findPaths(node: Node) {
        for (value in Direction.values()) {
            val next = getMove(value, Coordinate(node.x, node.y))
            val xRange = 0 until grid[0].count()
            val yRange = 0 until grid.count()
            if ((xRange.contains(next.x) && yRange.contains(next.y) && !hasVisitedNode(Coordinate(next.x, next.y)))) {
                val nextVal = grid[next.y][next.x]
                if (node.value == 'b' && (nextVal == 'a' || nextVal == 'S')) {
                    println("END")
                    println(node.depth + 1)
                }
//                println("Next: ${nextVal}, Current: ${node.value}, Diff ${node.value.code - nextVal.code}")
                if (node.value.code - nextVal.code <= 1) {
                    val childNode = Node(next.x, next.y, nextVal, node.depth + 1)
                    childNode.parent = node
                    node.children.add(childNode)
                    visitedNodes.add(childNode)
                    searchQueue.addLast(childNode)
                }
            }
        }
    }

    fun searchGraph() {
        if (graph != null) {
            searchQueue.addLast(graph!!)

            while(searchQueue.isNotEmpty()) {
                val node = searchQueue.removeFirst()
                findPaths(node)
            }
        }
    }

    fun printPath(node: Node) {
        println("${node.x} ${node.y}")
        if (node.parent != null) {
            printPath(node.parent!!)
        }
    }

    init {
        val lines = File("src/main/resources/input/12.txt").forEachLine {chars ->
            val row = chars.split("").filterNot { it == "" }.map() { it[0] }
            grid.add(row.toMutableList())
        }

        val startPosition = findStartPosition()
        if (startPosition != null) {
            val x = startPosition.x
            val y = startPosition.y
            graph = Node(x, y, 'z', 0)
            grid[y][x] = 'z'
        }
    }
}
