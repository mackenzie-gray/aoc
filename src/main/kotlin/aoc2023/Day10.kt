package aoc2023

import lib.Direction
import lib.Puzzle
import lib.Vector2
import java.util.LinkedList
import java.util.Queue
import java.util.Vector

class Day10: Puzzle() {

    class Pipe(var shape: Shape, pos: Vector2, var dist: Int? = 0) {
        var neighbours: List<Vector2> = shape.ends.map { dir ->
            pos.simpleNeighbours[dir]!!
        };

        enum class Shape(var ends: List<Direction>) {
            VERTICAL(listOf(Direction.NORTH, Direction.SOUTH)),
            HORIZONTAL(listOf(Direction.WEST, Direction.EAST)),
            BEND_J(listOf(Direction.NORTH, Direction.WEST)),
            BEND_L(listOf(Direction.NORTH, Direction.EAST)),
            BEND_7(listOf(Direction.WEST, Direction.SOUTH)),
            BEND_F(listOf(Direction.EAST, Direction.SOUTH));

            companion object {
                fun getShapeFromChar(c: Char): Shape? {
                    return when(c) {
                        '|' -> VERTICAL
                        '-' -> HORIZONTAL
                        'J' -> BEND_J
                        'L' -> BEND_L
                        'F' -> BEND_F
                        '7' -> BEND_7

                        else -> {
                            null
                        }
                    }
                }

                fun getShapeFromNeighbours(pos: Vector2, grid: Map<Vector2, Char>): Shape? {
                    var connected = pos.simpleNeighbours.filter { n ->
                        var char = grid[n.value]
                        if (char != null) {
                            var shape = getShapeFromChar(char)
                            if (shape != null) {
                                Pipe(shape, n.value).neighbours.contains(pos)
                            } else false
                        } else false
                    }
                    return Shape.values().find { it.ends.toSet() == connected.keys }
                }
            }
        }

    }

    override fun partA(): Long {
        var grid = mutableMapOf<Vector2, Char>();
        input.reversed().forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                grid[Vector2(x,y)] = c
            }
        }
        var sPos = grid.filter { it.value == 'S' }.keys.first()
        var sShape = Pipe.Shape.getShapeFromNeighbours(sPos, grid)!!
        var sPipe = Pipe(sShape, sPos, 0)
        var loop: MutableMap<Vector2, Pipe> = mutableMapOf()
        loop[sPos] = sPipe
        var queue = LinkedList<Pair<Vector2, Int>>();
        queue.addAll(sPipe.neighbours.map{ Pair(it, 1)})
        while(queue.isNotEmpty()) {
            var (pos, dist) = queue.pop()
            if (loop[pos] == null) {
                var shape = Pipe.Shape.getShapeFromChar(grid[pos]!!)!!
                loop[pos] = Pipe(shape, pos, dist)
                queue.addAll(loop[pos]!!.neighbours.map { it -> Pair(it, dist + 1) })
            }
        }

        var max = loop.maxBy { it.value.dist!! }.value.dist


        return max!!.toLong();
    }

    override fun partB(): Long {
        return 0L;
    }
}