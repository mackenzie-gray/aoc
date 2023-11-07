package aoc2022

import java.io.File

class Day21 {
    data class Monkey(
        val name: String,
        val left: String,
        val right: String,
        val operation: Operation
    ) {
        enum class Operation(val symbol: String) {
            Plus("+"), Minus("-"), Multiply("*"), Divide("/");

            companion object {
                fun getOperation(symbol: String): Operation {
                    Operation.values().forEach {
                        if(it.symbol == symbol) { return it }
                    }
                    return Plus
                }
            }
        }
    }

    val queue: ArrayDeque<Monkey> = ArrayDeque()
    val yellers: MutableMap<String, Long> = mutableMapOf()

    private fun calc(monkey: Monkey): Long? {
        val left = yellers[monkey.left]
        val right = yellers[monkey.right]
        if (left != null && right != null) {
            return when(monkey.operation) {
                Monkey.Operation.Plus -> left + right
                Monkey.Operation.Minus -> left - right
                Monkey.Operation.Multiply -> left * right
                Monkey.Operation.Divide -> left / right
            }
        }
        return null
    }

    init {
        val input = File("src/main/resources/input/21.txt").forEachLine { line ->
            val (name, value) = line.split(":").map {
                it.trim()
            }

            val operation = value.split(" ")
            if (operation.count() == 1) {
                yellers[name] = value.toLong()
            } else {
                val left = operation[0]
                val right = operation[2]
                val op = Monkey.Operation.getOperation(operation[1])
                queue.addLast(Monkey(name, left, right, op))
            }
        }

        while(queue.isNotEmpty()) {
            val monkey = queue.removeFirst()
            val result = calc(monkey)
            if (result == null) {
                queue.addLast(monkey)
            } else {
                yellers[monkey.name] = result
            }
        }

        println(yellers["root"])
    }
}