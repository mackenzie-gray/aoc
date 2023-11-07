package aoc2022

import java.io.File

class Day6 {
    init {
        val marker = ArrayDeque<Char>();
        run {
            File("src/main/resources/input/06.txt").inputStream().readBytes().forEachIndexed { index, it ->
                val char = it.toInt().toChar()
                while (marker.contains(char)) {
                    marker.removeFirst();
                }
                marker.addLast(char)
                if (marker.count() == 14) {
                    println(index + 1)
                    return@run
                }
            }
        }
    }
}