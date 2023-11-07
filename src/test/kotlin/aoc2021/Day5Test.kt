package aoc2021

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day5Test {
    @Test
    fun partAMini() {
        val instance = Day5("src/test/resources/input/aoc2021/mini/Day5");
        assertEquals(5, instance.partA())
    }

    @Test
    fun partA() {
        val instance = Day5("src/test/resources/input/aoc2021/full/Day5");
        assertEquals(5124, instance.partA())
    }

    @Test
    fun partBMini() {
        val instance = Day5("src/test/resources/input/aoc2021/mini/Day5");
        assertEquals(12, instance.partB())
    }

    @Test
    fun partB() {
        val instance = Day5("src/test/resources/input/aoc2021/full/Day5");
        assertEquals(19771, instance.partB())
    }
}
