package aoc2021;

import lib.Line
import lib.Vector2
import java.io.File

class Day5(private var inputFileName: String) {
    private var input: List<String> = File(inputFileName).readLines();

    var lines = input.map {
        val p = it.split(" -> ").map { s ->
            s.split(",")
        }
        val p1 = p[0]
        val p2 = p[1]
        val v1 = Vector2(p1[0].toInt(), p1[1].toInt())
        val v2 = Vector2(p2[0].toInt(), p2[1].toInt())
        Line(v1, v2)
    }

    fun partA(): Int {
        var cells: MutableMap<Vector2, Int> = mutableMapOf();
        var set = lines.filter { it.isHorizontal || it.isVertical }
        set.forEach() { line ->
            line.getPoints().forEach { p ->
                if (cells[p] == null) {
                    cells[p] = 1
                } else {
                    cells[p] = cells[p]!! + 1
                }
            }
        }

        return cells.filter { it ->
            it.value >= 2
        }.size
    }

    fun partB(): Int {
        var cells: MutableMap<Vector2, Int> = mutableMapOf();
        lines.forEach() { line ->
            line.getPoints().forEach { p ->
                if (cells[p] == null) {
                    cells[p] = 1
                } else {
                    cells[p] = cells[p]!! + 1
                }
            }
        }

        return cells.filter { it ->
            it.value >= 2
        }.size
    }
}

