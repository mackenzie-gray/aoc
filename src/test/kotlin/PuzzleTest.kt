import lib.Puzzle
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

abstract class PuzzleTest(
    private val instance: Puzzle,
    var name: String,
    var aMini: Long,
    var aFull: Long,
    var bMini: Long,
    var bFull: Long
) {
    var fileBase = "src/test/resources/input/aoc2021"

    @Test
    fun partAMini() {
        instance.loadInput("$fileBase/mini/$name");
        assertEquals(aMini, instance.partA())
    }
    @Test
    fun partA() {
        instance.loadInput("$fileBase/full/$name");
        assertEquals(aFull, instance.partA())
    }
    @Test
    fun partBMini() {
        instance.loadInput("$fileBase/mini/$name");
        assertEquals(bMini, instance.partB())
    }
    @Test
    fun partB() {
        instance.loadInput("$fileBase/full/$name");
        assertEquals(bFull, instance.partB())
    }
}