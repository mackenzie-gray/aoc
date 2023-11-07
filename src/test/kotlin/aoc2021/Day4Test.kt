package aoc2021

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day4Test {
    @Test
    fun partAMini() {
        val instance = Day4("src/test/resources/input/aoc2021/mini/Day4");
        assertEquals(4512, instance.partA())
    }

    @Test
    fun partA() {
        val instance = Day4("src/test/resources/input/aoc2021/full/Day4");
        assertEquals(31424, instance.partA())
    }

    @Test
    fun partBMini() {
        val instance = Day4("src/test/resources/input/aoc2021/mini/Day4");
        assertEquals(1924, instance.partB())
    }

    @Test
    fun partB() {
        val instance = Day4("src/test/resources/input/aoc2021/full/Day4");
        assertEquals(23042, instance.partB())
    }
}
