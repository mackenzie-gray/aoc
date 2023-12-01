import lib.Puzzle
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

abstract class PuzzleTest(
    private val solution: Class<out Puzzle>,
    var name: String,
    var aMini: Long,
    var aFull: Long,
    var bMini: Long,
    var bFull: Long,
    var year: String? = "2021",
) {
    var fileBase = "src/test/resources/input/aoc$year"

    @Test
    fun partAMini() {
        var instance = solution.getDeclaredConstructor().newInstance()
        instance.loadInput("$fileBase/mini/$name");
        assertEquals(aMini, instance.partA())
    }

    @Test
    fun partA() {
        var instance = solution.getDeclaredConstructor().newInstance()
        instance.loadInput("$fileBase/full/$name");
        assertEquals(aFull, instance.partA())
    }

    @Test
    fun partBMini() {
        var instance = solution.getDeclaredConstructor().newInstance()
        instance.loadInput("$fileBase/mini/$name");
        assertEquals(bMini, instance.partB())
    }
    @Test
    fun partB() {
        var instance = solution.getDeclaredConstructor().newInstance()
        instance.loadInput("$fileBase/full/$name");
        assertEquals(bFull, instance.partB())
    }
}