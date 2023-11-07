package days

import lib.Direction
import lib.Vector2
import java.io.File
import kotlin.math.pow

class Day25 {

    enum class Digit(var value: Int, var char: Char) {
        DOUBLE_MINUS(-2, '='),
        MINUS(-1, '-'),
        ZERO(0, '0'),
        ONE(1, '1'),
        TWO(2, '2');

        companion object {
            fun get(char: Char): Digit? {
                return Digit.values().find {
                    it.char == char
                }
            }

        }
    }

    private fun parseLine(line: String): Int {
        val chars = line.reversed();
        var total = 0;
        chars.forEachIndexed { index, c ->
            val base = 5.0.pow(index);
            val digit = Digit.get(c)!!.value;
            total += (base * digit).toInt()
        }
        return total
    }

    private fun convert(num: Int) {
        var numDigits = 0;
        var max = 0;
        var digit = "";
    }


    init {
        val lines = readFile("src/main/resources/input/mini.txt");
        var sum = 0;
        lines.forEach {
            val value = parseLine(it)
            sum += value
        }
        println(sum)
    }

    private fun readFile(file: String): List<String> {
        val fileObj = File(file)
        return fileObj.readLines()
    }
}