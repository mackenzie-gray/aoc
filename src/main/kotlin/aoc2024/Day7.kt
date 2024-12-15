package aoc2024

import lib.Puzzle
import java.util.*


class Day7 : Puzzle() {
    override fun partA(): Long {
        return input.fold(0) { acc, s ->
            val (sol, rest) = s.split(": ")
            val args = rest.split(" ").map { it.toLong() }.toCollection(ArrayDeque())
            if (canSolve(sol.toLong(), args)) {
                acc + sol.toLong()
            } else {
                acc
            }
        }
    }

    override fun partB(): Long {
        return input.fold(0) { acc, s ->
            val (sol, rest) = s.split(": ")
            val args = rest.split(" ").map { it.toLong() }.toCollection(ArrayDeque())
            if (canSolve(sol.toLong(), args, true)) {
                acc + sol.toLong()
            } else {
                acc
            }
        }
    }

    fun canSolve(solution: Long, args: ArrayDeque<Long>, concat: Boolean = false): Boolean {
        val a = args.pop()
        val b = args.pop()
        if (args.isEmpty()) {
            return a + b == solution || a * b == solution || (concat && "${a}${b}".toLong() == solution)
        }

        val add = a + b
        val addQ = args.clone()
        addQ.push(add);

        val mult = a * b
        val multQ = args.clone()
        multQ.push(mult);

        val concatV = "${a}${b}".toLong()
        val concatQ = args.clone()
        concatQ.push(concatV)

        return (add <= solution && canSolve(solution, addQ, concat)) || (mult <= solution && canSolve(solution, multQ, concat)) || (concat && concatV <= solution && canSolve(solution, concatQ, true))
    }
}