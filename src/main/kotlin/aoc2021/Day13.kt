package aoc2021

import lib.Puzzle
import lib.Vector2

class Day13: Puzzle() {

    private fun foldX(x: Float, dots: MutableSet<Vector2>): MutableSet<Vector2> {
        var newDots = mutableSetOf<Vector2>();
        dots.forEach {
            if (it.x > x) {
                var newDot = Vector2(it.x - ((it.x - x) * 2), it.y);
                newDots.add(newDot)
            } else { newDots.add(it) }
        }
        return newDots
    }

    private fun foldY(y: Float, dots: MutableSet<Vector2>): MutableSet<Vector2> {
        var newDots = mutableSetOf<Vector2>();
        dots.forEach {
            if (it.y > y) {
                var newDot = Vector2(it.x, it.y - ((it.y - y) * 2));
                newDots.add(newDot)
            } else { newDots.add(it) }
        }
        return newDots
    }

    private fun printGrid(dots: Set<Vector2>) {
        var xMax = dots.maxBy { it.x }.x
        var yMax = dots.maxBy { it.y }.y
        for (y in 0..yMax.toInt()) {
            for (x in 0 .. xMax.toInt()) {
                if (dots.contains(Vector2(x,y))) {
                    print("#")
                } else {
                    print(".")
                }
            }
            print("\n")
        }
    }

    override fun partA(): Long {
        val splitIndex = input.indexOf("");
        var dots = input.subList(0, splitIndex).map {
            val c = it.split(",")
            Vector2(c[0].toInt(), c[1].toInt())
        }.toMutableSet();
        var folds = input.subList(splitIndex + 1, input.size).map() {
            it.split("fold along ")[1].split("=")
        }
        folds.forEach {
            if (it[0] == "x") {
                var newDots = foldX(it[1].toFloat(), dots)
                dots = newDots
            } else if (it[0] == "y") {
                var newDots = foldY(it[1].toFloat(), dots)
                dots = newDots
            }
        }
        printGrid(dots)
        return dots.size.toLong()
    }

    override fun partB(): Long {
        return 0L
    }
}