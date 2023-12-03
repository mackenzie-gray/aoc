package aoc2023

import lib.Puzzle
import lib.Vector2

class Day3: Puzzle() {

    class PartNumber(
        var y: Int,
        var xRange: IntRange,
        var value: Int
    ) {
        var included: Boolean = false
    }

    override fun partA(): Long {
        val symbols = mutableListOf<Vector2>();
        val parts = mutableListOf<PartNumber>();

        input.forEachIndexed {y, line ->
            val numRegex = Regex("\\d+")
            numRegex.findAll(line).forEach { match ->
                parts.add(PartNumber(y, match.range, match.value.toInt()))
            }

            val symbolRegex = Regex("[^\\w\\s.]")
            symbolRegex.findAll(line).forEach { match ->
                symbols.add(Vector2(match.range.first, y))
            }
        }

        symbols.forEach {symbol ->
            symbol.neighbours.values.forEach {
                val adjPart = parts.find {part ->
                    part.y == it.y.toInt() &&
                            part.xRange.contains(it.x.toInt())
                }
                if (adjPart != null) {
                    adjPart.included = true
                }
            }
        }

        return parts.filter { it.included }.sumOf { it.value }.toLong()
    }

    override fun partB(): Long {
        val gears = mutableMapOf<Vector2, MutableSet<PartNumber>>();
        val parts = mutableListOf<PartNumber>();

        input.forEachIndexed {y, line ->
            val numRegex = Regex("\\d+")
            numRegex.findAll(line).forEach { match ->
                parts.add(PartNumber(y, match.range, match.value.toInt()))
            }

            val gearRegex = Regex("\\*")
            gearRegex.findAll(line).forEach { match ->
                gears[Vector2(match.range.first, y)] = mutableSetOf()
            }
        }

        gears.forEach {(pos, set) ->
            pos.neighbours.values.forEach {
                val adjPart = parts.find {part ->
                    part.y == it.y.toInt() &&
                            part.xRange.contains(it.x.toInt())
                }

                if (adjPart != null && !adjPart.included) {
                    adjPart.included = true
                    set.add(adjPart)
                }
            }
        }

        return gears.filter { it.value.size == 2 }.values.sumOf {
            it.first().value * it.last().value
        }.toLong()
    }

}