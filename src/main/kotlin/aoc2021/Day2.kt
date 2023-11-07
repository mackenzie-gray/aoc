package aoc2021

import lib.Direction
import lib.Vector2
import java.io.File
import java.math.BigDecimal

class Day2(private var inputFileName: String) {

    private var position = Vector2(0, 0);
    private var aim = 0;

    init {
        val lines = File(inputFileName).readLines();
        parseInput(lines);
    }

    fun getPosition(): BigDecimal {
        var forward = position.x.toBigDecimal();
        var depth = (position.y * -1).toBigDecimal();
        println("$forward $depth")
        return forward * depth ;
    }


    private fun processMove(direction: String, amount: Int) {
        var depth = aim * amount
        when(direction) {
            "forward" -> {
                position += (Direction.EAST.vec * amount)
                position += (Direction.SOUTH.vec * depth)
            }
            "down" -> {
                aim += amount
            }
            "up" -> {
                aim -= amount
            }
        }
    }

    private fun parseInput(lines: List<String>) {
        lines.forEach() {
            val input = it.split(" ")
            processMove(input[0], input[1].toInt());
        }

    }
}