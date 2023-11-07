package days

import java.io.File
import java.util.*
import kotlin.collections.ArrayDeque

class Day10 {
    val instructionStack = ArrayDeque<String>()
    var register = 1
    var clock = 0
    var instructionStartTime = 0
    var cumSignalStrength = 0
    val screen = Array(6){Array(40) {"."}}

    init {
        File("src/main/resources/input/10.txt").forEachLine{ line ->
            instructionStack.addLast(line)
        }
    }

    fun popInstruction() {
        instructionStack.removeFirst()
        instructionStartTime = clock + 1
    }

    fun doNoop() {
        popInstruction()
    }

    fun doAddX(value: Int) {
        if (clock == instructionStartTime + 1) {
            register += value
            popInstruction()
        }
    }

    fun printScreen() {
        screen.forEach {row ->
            row.forEach {
                print("${it} ")
            }
            print("\n")
        }
    }

    fun calcSignalStrength(cycle: Int, value: Int) {
        println("Cyle: ${cycle}, RegisterX: ${value}, SignalStrength: ${cycle * value}")
        cumSignalStrength += cycle * value
    }

    fun runProgram() {
        clock = 0
        instructionStartTime = 0

        while(clock <= 240) {

            if (instructionStack.isEmpty()) break;

            val screenX = clock % 40
            val screenY = clock / 40

            val spriteRange = register - 1 .. register + 1

            if (spriteRange.contains(screenX)) {
                screen[screenY][screenX] = "#"
            }

            val cmd = instructionStack.get(0).split(" ")
            val instruction = cmd[0]
            if (instruction == "noop") {
                doNoop()
            } else {
                doAddX(cmd[1].toInt())
            }
            clock++
        }
    }
}