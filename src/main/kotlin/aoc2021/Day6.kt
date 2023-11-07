package aoc2021;

import java.io.File

class Day6(private var inputFileName: String) {
    private var input: List<String> = File(inputFileName).readLines();

    private var school: MutableMap<Int, Long?> = mutableMapOf(
        0 to 0,
        1 to 0,
        2 to 0,
        3 to 0,
        4 to 0,
        5 to 0,
        6 to 0,
        7 to 0,
        8 to 0,
    )
     init {
         input[0].split(',').forEach() {
             school[it.toInt()] = school[it.toInt()]!! + 1
         }
     }

    private fun simulateDay() {
        var newSchool = mutableMapOf(
            0 to school[1],
            1 to school[2],
            2 to school[3],
            3 to school[4],
            4 to school[5],
            5 to school[6],
            6 to (school[7]!! + school[0]!!),
            7 to school[8],
            8 to school[0]
        )
        school = newSchool
    }

    private fun countFish(): Long {
        return school.values.reduce { acc, i ->
            if (i != null) {
                acc?.plus(i)
            } else {
                acc
            }
        }!!
    }

    fun partA(): Long {
        for (i in 1..80) {
            simulateDay()
        }
        return countFish()
    }

    fun partB(): Long {
        for (i in 1 .. 256) {
            simulateDay()
        }
        return countFish()
    }
}

