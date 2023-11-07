package aoc2021

import java.io.File

class Day1(private var inputFileName: String) {

    private var depthMap: MutableList<Int> = mutableListOf();
    init {
        val lines = File(inputFileName).readLines();
        parseInput(lines);
        println(depthMap);
    }

    public fun countDepthIncreases(): Int {
        var depthIncreases = 0;
        for (i in 1 until depthMap.size) {
            if (depthMap[i] > depthMap[i-1]) {
                depthIncreases += 1;
            }
        }
        return depthIncreases
    }

    public fun countWindowDepthIncreases(): Int {
        var depthIncreases = 0;
        for (i in 0 until depthMap.size - 3) {
            var windowA = depthMap[i] + depthMap[i + 1] + depthMap[i + 2]
            var windowB = depthMap[i + 1] + depthMap[i + 2] + depthMap[i + 3]
            if (windowB > windowA) {
                depthIncreases += 1;
            }
        }
        return depthIncreases
    }

    private fun parseInput(lines: List<String>) {
        lines.forEach {
            depthMap.add(it.toInt());
        }
    }
}