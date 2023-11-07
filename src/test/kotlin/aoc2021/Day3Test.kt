package aoc2021

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day3Test {
    @Test
    fun partAMini() {
        val instance = Day3("src/test/resources/input/aoc2021/mini/Day3");
        assertEquals(198, instance.partA())
    }

    @Test
    fun partA() {
        val instance = Day3("src/test/resources/input/aoc2021/full/Day3");
        assertEquals(2261546, instance.partA())
    }

    @Test
    fun partBMini() {
        val instance = Day3("src/test/resources/input/aoc2021/mini/Day3");
        assertEquals(230, instance.partB())
    }

    @Test
    fun partB() {
        val instance = Day3("src/test/resources/input/aoc2021/full/Day3");
        assertEquals(6775520, instance.partB())
    }
}