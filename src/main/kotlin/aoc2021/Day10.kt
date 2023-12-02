package aoc2021

import lib.Direction
import lib.Puzzle
import lib.Vector2
import java.util.Deque

class Day10: Puzzle() {
    var tags = mapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}',
        '<' to '>',
    )

    var score: Map<Char, Long> = mapOf(
        ')' to 3,
        ']' to 57,
        '}' to 1197,
        '>' to 25137,
    )

    private fun isOpening(tag: Char): Boolean {
        return tags.keys.contains(tag)
    }

    private fun findCorruptLine(line: String): Char? {
        var closingQueue = ArrayDeque<Char>();

        var corruptChar: Char? = null
        run check@{
            line.forEach { c ->
                if (isOpening(c)) {
                    closingQueue.addFirst(tags[c]!!)
                } else {
                    if (c == closingQueue.first()) {
                        closingQueue.removeFirst()
                    } else {
                        corruptChar = c
                        return@check;
                    }
                }
            }
        }

        return corruptChar
    }

    private fun findIncompleteLine(line: String): String? {
        var closingQueue = ArrayDeque<Char>();

        var corruptChar: Char? = null
        run check@{
            line.forEach { c ->
                if (isOpening(c)) {
                    closingQueue.addFirst(tags[c]!!)
                } else {
                    if (c == closingQueue.first()) {
                        closingQueue.removeFirst()
                    } else {
                        corruptChar = c
                        return@check;
                    }
                }
            }
        }

        if (corruptChar != null) {
            return null
        } else {
            return closingQueue.joinToString("")
        }

    }

    fun findCompletionScore(line: String): Long {
        val charScore = mapOf<Char, Long>(
            ')' to 1,
            ']' to 2,
            '}' to 3,
            '>' to 4,
        )
        return line.fold(0L) { acc, c ->
            (acc * 5) + charScore[c]!!
        }
    }

    override fun partA(): Long {
        return input.map { line ->
            findCorruptLine(line)
        }.filterNotNull().map { c ->
            score[c]
        }.filterNotNull().fold(0) { acc, l ->
            acc + l
        }
    }

    override fun partB(): Long {
        var scores = input.map { line ->
            findIncompleteLine(line)
        }.filterNotNull().map { s ->
            findCompletionScore(s)
        }.sorted()
        return scores[scores.size / 2]
    }
}