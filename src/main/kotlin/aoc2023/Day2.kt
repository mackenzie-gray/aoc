package aoc2023

import lib.Puzzle

class Day2: Puzzle(

) {
    override fun partA(): Long {
        var possibleGames = input.filter { line ->
            var pieces = line.split(": ")
            var game = pieces[1].split(" ")
            var possible = true
            run loop@{
                game.forEachIndexed { index, s ->
                    var int = s.toIntOrNull()
                    if ( int != null && int > 12) {
                        if (int > 14) {
                            possible = false
                            return@loop
                        } else if (int > 12 && game[index+1].startsWith("red")) {
                            possible = false
                            return@loop
                        } else if (int > 13 && game[index+1].startsWith("green")) {
                            possible = false
                            return@loop
                        }
                    }
                }
            }
            possible
        }

        return possibleGames.sumOf {
            it.split(": ")[0].split(" ")[1].toLong()
        }
    }

    override fun partB(): Long {
        return input.sumOf { line ->
            val pieces = line.split(": ")
            val game = pieces[1].split(" ")

            val maxVals = mutableMapOf(
                "red" to 0,
                "green" to 0,
                "blue" to 0
            )

            game.forEachIndexed { i, s ->
                var int = s.toIntOrNull()
                if (int != null){
                    var color = game[i + 1].trimEnd(',', ';')
                    if (int > maxVals[color]!!) {
                        maxVals[color] = int
                    }

                }
            }

            maxVals.values.fold(1L) { acc, it ->
                it * acc
            }
        }
    }

}