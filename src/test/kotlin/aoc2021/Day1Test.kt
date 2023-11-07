package aoc2021

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day1Test {
    @Test
    fun partAMini() {
        val instance = Day1("src/test/resources/input/aoc2021/mini/Day1");
        val count = instance.countDepthIncreases();
        assertEquals(7, count);
    }

    @Test
    fun partA() {
        val instance = Day1("src/test/resources/input/aoc2021/full/Day1");
        val count = instance.countDepthIncreases();
        assertEquals(1226, count);
    }

    @Test
    fun partBMini() {
        val instance = Day1("src/test/resources/input/aoc2021/mini/Day1");
        val count = instance.countWindowDepthIncreases();
        assertEquals(5, count);
    }

    @Test
    fun partB() {
        val instance = Day1("src/test/resources/input/aoc2021/full/Day1");
        val count = instance.countWindowDepthIncreases();
        assertEquals(1252, count);
    }
}