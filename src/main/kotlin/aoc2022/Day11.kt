package aoc2022

import java.io.File

class Day11 {

    enum class Operation {
        Product, Add, Old;

        companion object {
            fun parseOp(op: List<String>): Pair<Operation, Long> {
                return if (op[2] == "old") {
                    Pair(Old, 0L)
                } else if (op[1] == "*") {
                    Pair(Product, op[2].toLong())
                } else {
                    Pair(Add, op[2].toLong())
                }
            }
        }
    }
    class Monkey(
        val items: ArrayDeque<Long>,
        private val operation: Pair<Operation, Long>,
        val testValue: Int,
        val trueMonkey: Int,
        val falseMonkey: Int
    ) {
        var inspections = 0L
        fun doOperation(item: Long): Long {
            return when(operation.first) {
                Operation.Product -> item * operation.second
                Operation.Add -> item + operation.second
                Operation.Old -> item * item
            }
        }
    }

    private val monkeys: MutableList<Monkey> = mutableListOf()
    private var lcm: Long = 0

    fun inspectAndThrow(monkey: Monkey) {
        val item = monkey.items.removeFirst()
        val newWorryLevel = monkey.doOperation(item) % lcm
        val remainder = newWorryLevel % monkey.testValue
        if (remainder == 0L) {
            monkeys[monkey.trueMonkey].items.addLast(newWorryLevel)
        } else {
            monkeys[monkey.falseMonkey].items.addLast(newWorryLevel)
        }
        monkey.inspections++
    }

    fun playRound() {
        monkeys.forEach() {monkey ->
            while(monkey.items.isNotEmpty()) {
                inspectAndThrow(monkey)
            }
        }
    }

    fun playRounds(numRounds: Int) {
        for(i in 1..numRounds) {
            playRound()
        }
    }

    fun getActiveMonkeys() {
        val counts: List<Long> = monkeys.map {
            it.inspections
        }.sortedDescending()
        println(counts)
        println(counts[0] * counts[1])
    }


    // gross.tm
    init {
        val lines = File("src/main/resources/input/11.txt").readLines()
        for (i in 0..lines.count() step 7) {
            val startingItems = lines[i+1].split(":")[1].split(", ").map { it.trim().toLong() }
            val operation = Operation.parseOp(lines[i+2].split(":")[1]
                .split("=")[1].trim().split(" ").map{ it.trim() })
            val testValue = lines[i+3].split(" ").last().toInt()
            val trueMonkey = lines[i+4].split(" ").last().toInt()
            val falseMonkey = lines[i+5].split(" ").last().toInt()
            val monkey = Monkey(ArrayDeque(startingItems), operation, testValue, trueMonkey, falseMonkey)
            monkeys.add(monkey)
        }
        lcm = monkeys.map { it.testValue }.distinct().fold(1) { acc, int ->
            int * acc
        }
    }
}