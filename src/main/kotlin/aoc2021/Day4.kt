package aoc2021

import lib.Direction
import lib.Vector2
import java.io.File
import java.math.BigDecimal

class Day4(private var inputFileName: String) {
    private var input: List<String> = File(inputFileName).readLines();

    private var boards: MutableList<Board> = mutableListOf()
    private var numbers: MutableList<Int> = input[0].split(",").map { it.toInt() }.toMutableList()

    private var lastCalled = 0

    private var firstBoardToWin: Board? = null;
    private var lastBoardToWin: Board? = null;
    private var lastBoardToWinScore: Int? = null;

    init {
        val subInput = input.subList(2,input.size)
        for(i in subInput.indices step 6) {
            if (i + 5 <= subInput.size) {
                boards.add(Board.createBoard(subInput.subList(i, i + 5)))
            }
        }
    }

    private class Board {
        var valueLocations: MutableMap<Number, Vector2> = mutableMapOf()
        var valueMarked: MutableMap<Number, Boolean> = mutableMapOf()
        var markedLocations: MutableList<Vector2> = mutableListOf()

        fun hasBingo(): Boolean {
            val yCounts = mutableMapOf(0 to 0, 1 to 0, 2 to 0, 3 to 0, 4 to 0)
            val xCounts = mutableMapOf(0 to 0, 1 to 0, 2 to 0, 3 to 0, 4 to 0)

            var hasBingo = false
            markedLocations.forEach {
                yCounts[it.y.toInt()] = yCounts[it.y.toInt()]!! + 1
                xCounts[it.x.toInt()] = xCounts[it.x.toInt()]!! + 1

                if (yCounts[it.y.toInt()] == 5 || xCounts[it.x.toInt()] == 5) {
                    hasBingo = true
                }
            }
            return hasBingo
        }

        fun markNumber(num: Number) {
            valueLocations[num]?.let {
                valueMarked[num] = true
                markedLocations.add(it)
            }
        }

        fun getScore(): Number {
            val unmarked = valueMarked.filter { !it.value }
            val result = unmarked.keys.toTypedArray().reduce { acc, number ->
                number.toInt() + acc.toInt()
            }
            return result
        }

        companion object {
            fun createBoard(input: List<String>): Board {
                val board = Board();
                input.forEachIndexed { y, strings ->
                    strings.split(' ').filter { it != "" }.forEachIndexed { x, value ->
                        val int = value.toInt()
                        board.valueLocations[int] = Vector2(x,y)
                        board.valueMarked[int] = false
                    }
                }
                return board
            }
        }
    }

    private fun playBingo(): Int {
        var firstBingoScore = 0
        numbers.forEach { num ->
            boards.forEach {
                if (!it.hasBingo()) {
                    it.markNumber(num)
                    if (it.hasBingo()) {
                        if (firstBingoScore == 0) {
                           firstBingoScore = it.getScore().toInt() * num
                        }
                        lastBoardToWin = it
                        lastBoardToWinScore = it.getScore().toInt() * num
                    }
                }
            }
        }
        return firstBingoScore
    }

    fun partA(): Int {
        return playBingo()
    }

    fun partB(): Int {
        playBingo()
        return lastBoardToWinScore!!
    }
}