package aoc2021

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day2Test {
    @Test
    fun partAMini() {
        val instance = Day2("src/test/resources/input/aoc2021/mini/Day2");
        val count = instance.getPosition().toInt();
        assertEquals(900, count);
    }

    @Test
    fun partA() {
        val instance = Day2("src/test/resources/input/aoc2021/full/Day2");
        val count = instance.getPosition().toInt();
        assertEquals(1960569556, count);
    }
}