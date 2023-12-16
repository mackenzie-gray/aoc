package aoc2023

import PuzzleTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day8Test: PuzzleTest(
    Day8::class.java,
    "Day8",
    6,
    19667,
    6,
    0,
    "2023"
) {
    @Test
    override fun partBMini() {
        var instance = solution.getDeclaredConstructor().newInstance()
        instance.loadInput("$fileBase/mini/${name}PartB");
        assertEquals(bMini, instance.partB())
    }

    @Test
    override fun partB() {
        var instance = solution.getDeclaredConstructor().newInstance()
        instance.loadInput("$fileBase/full/${name}");
        assertEquals(bMini, instance.partB())
    }
}