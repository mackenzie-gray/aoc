package aoc2024

import lib.Puzzle
import lib.Vector2

class Day8 : Puzzle() {
    private var bounds = Vector2(0, 0)
    private var map: MutableMap<Char, MutableList<Vector2>> = mutableMapOf();

    override fun loadInput(fileName: String) {
        super.loadInput(fileName)

        bounds = Vector2(input[0].length - 1, input.size - 1)

        input.reversed().forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                if (c != '.') {
                    val pos = Vector2(x, y)
                    if (map.containsKey(c)) {
                        map[c]?.add(pos)
                    } else {
                        map[c] = mutableListOf(pos)
                    }
                }
            }
        }
    }

    fun findANodes(a: Vector2, b: Vector2): List<Vector2> {
        val dx = (a.x - b.x)
        val dy = (a.y - b.y)

        val nodes = mutableListOf<Vector2>()
        val a1 = Vector2(a.x + dx, a.y + dy)
        val a2 = Vector2(b.x - dx, b.y - dy)

        nodes.add(a1)
        nodes.add(a2)

        return nodes.filter() { valid(it) }
    }

    fun findAllANodes(a: Vector2, b: Vector2): List<Vector2> {
        val dx = (a.x - b.x)
        val dy = (a.y - b.y)

        val nodes = mutableListOf<Vector2>()
        var a1 = Vector2(a.x + dx, a.y + dy)
        var a2 = Vector2(b.x - dx, b.y - dy)

        while(valid(a1)) {
            nodes.add(a1)
            a1 = Vector2(a1.x + dx, a1.y + dy)
        }

        while(valid(a2)) {
            nodes.add(a2)
            a2 = Vector2(a2.x - dx, a2.y - dy)
        }

        return nodes.filter() { valid(it) }
    }


    private fun valid(pos: Vector2): Boolean {
        return pos.x >= 0 && pos.y >= 0 && pos.x <= bounds.x && pos.y <= bounds.y
    }

    override fun partA(): Long {
        val validANodes = mutableSetOf<Vector2>()
        map.entries.forEach { (_, nodes) ->
            val nodeQ = nodes.toCollection(ArrayDeque())
            while (nodeQ.isNotEmpty()) {
                val node = nodeQ.removeFirst()
                nodeQ.forEach { o ->
                    validANodes.addAll(findANodes(node, o));
                }
            }
        }

        return validANodes.size.toLong()
    }

    override fun partB(): Long {
        val validANodes = mutableSetOf<Vector2>()
        map.entries.forEach { (_, nodes) ->
            val nodeQ = nodes.toCollection(ArrayDeque())
            while (nodeQ.isNotEmpty()) {
                val node = nodeQ.removeFirst()
                nodeQ.forEach { o ->
                    validANodes.addAll(findAllANodes(node, o));
                }
            }
        }

        validANodes.addAll(map.values.flatten())
        return validANodes.size.toLong()
    }
}