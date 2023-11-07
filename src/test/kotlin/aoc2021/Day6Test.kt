package aoc2021

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day6Test {
    @Test
    fun partAMini() {
        val instance = Day6("src/test/resources/input/aoc2021/mini/Day6");
        assertEquals(5934, instance.partA())
    }

    @Test
    fun partA() {
        val instance = Day6("src/test/resources/input/aoc2021/full/Day6");
        assertEquals(380758, instance.partA())
    }

    @Test
    fun partBMini() {
        var exp = 26984457539
        val instance = Day6("src/test/resources/input/aoc2021/mini/Day6");
        assertEquals(26984457539, instance.partB());
    }

    @Test
    fun partB() {
        val instance = Day6("src/test/resources/input/aoc2021/full/Day6");
        assertEquals(1710623015163, instance.partB())
    }
}
