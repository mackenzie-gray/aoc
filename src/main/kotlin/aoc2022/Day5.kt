package aoc2022

import java.io.File

class Day5 {
    private val stacks: ArrayList<ArrayDeque<Char>> = ArrayList();

    init {
        File("src/main/resources/input/05.txt").useLines() { lines ->
            lines.forEach { line ->
                if(line.contains("[")) {
                    line.chunkedSequence(4).forEachIndexed { index, s ->
                        if (stacks.count() <= index) {
                            stacks.add(ArrayDeque())
                        }
                        val item = s[1]
                        if (item != ' ') {
                            stacks[index].addLast(item)
                        }
                    }
                } else if (line.isNotEmpty() && line[0] == 'm') {
                    processMove9001(line)
                }
            }
        }
    }

    private fun processMove9000(move: String) {
        val moveQueue = ArrayDeque<Char>();
        val data = move.split(" ")
        val count = data[1].toInt()
        val from = data[3].toInt()
        val to = data[5].toInt()

        for(i in 1..count) {
            moveQueue.addLast(stacks[from-1].removeFirst())
        }

        for(i in 1..count) {
            stacks[to-1].addFirst(moveQueue.removeFirst())
        }
    }

    private fun processMove9001(move: String) {
        val moveQueue = ArrayDeque<Char>();
        val data = move.split(" ")
        val count = data[1].toInt()
        val from = data[3].toInt()
        val to = data[5].toInt()

        for(i in 1..count) {
            moveQueue.addLast(stacks[from-1].removeFirst())
        }

        for(i in 1..count) {
            stacks[to-1].addFirst(moveQueue.removeLast())
        }
    }

    public fun getTopOfStacks() {
        val result = stacks.forEach{
            print(it.firstOrNull())
        }
    }
}