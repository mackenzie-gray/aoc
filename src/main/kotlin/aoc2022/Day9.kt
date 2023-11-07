package aoc2022

import java.io.File
import java.util.*
import kotlin.math.sign

typealias Coordinate = Pair<Int, Int>
class Day9 {
    private var knots: Array<Coordinate> = Array(10) { Coordinate(0,0) }
    private val tailPositionLog: MutableSet<Coordinate> = mutableSetOf(Coordinate(0,0))

    enum class Direction {
        U, D, R, L
    }

    fun isTouchingLeader(index: Int): Boolean {
        val xRange = knots[index-1].first - 1 .. knots[index-1].first + 1
        val yRange = knots[index-1].second - 1 .. knots[index-1].second + 1
        return xRange.contains(knots[index].first) && yRange.contains(knots[index].second)
    }

    fun performHeadMove(dir: Direction) {
        val head = knots[0]
        knots[0] = when(dir) {
            Direction.U -> Pair(head.first, head.second + 1)
            Direction.D -> Pair(head.first, head.second - 1)
            Direction.L -> Pair(head.first - 1, head.second)
            Direction.R -> Pair(head.first + 1, head.second)
        }
        for (i in 1 until knots.count()) {
            performFollowerMove(i, dir)
        }
    }

    fun performFollowerMove(index: Int, dir: Direction) {
        if (dir == Direction.L) println(isTouchingLeader(index))
        if (!isTouchingLeader(index)) {
            val head = knots[index-1]
            val tail = knots[index]

            val newX = (head.first - tail.first).sign + tail.first
            val newY = (head.second - tail.second).sign + tail.second

            knots[index] = Pair(newX, newY)

            if (index == knots.count() - 1) {
                tailPositionLog.add(knots[index])
            }
        }
    }

    fun performMove(dir: Direction, times: Int) {
        for(i in 1..times) {
            performHeadMove(dir)
            if (dir == Direction.L) {
                println(Arrays.toString(knots))
            }
        }
    }

    fun printTailPositionLogCount() {
        println(tailPositionLog.count())
    }

    init {
        File("src/main/resources/input/09.txt").forEachLine{ line ->
            val move = line.split(" ")
            println("${move[0]} ${move[1]}")
            performMove(Direction.valueOf(move[0]), move[1].toInt())
            println(Arrays.toString(knots))
        }
    }

}