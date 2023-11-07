package days

import java.io.File
import java.util.Arrays
import kotlin.collections.ArrayList


class Day8 {
    class Tree(var height: Int) {
        var visible: Boolean = false
    }

    private var forest: ArrayList<ArrayList<Tree>> = arrayListOf()
    init {
        File("src/main/resources/input/08.txt").forEachLine{  line ->
            val row = ArrayList<Tree>();
            line.chars().forEach{
                row.add(Tree(it.toChar().toString().toInt()))
            }
            forest.add(row)
        }
    }

    fun processTreeVisibility(): Int {
        var visibleTrees = 0
        var colTallestTop = Array(forest.count()) { 0 }
        var colTallestBot = Array(forest.count()) { 0 }
        forest.forEachIndexed() { i, row ->
            var rowTallestLeft = 0
            var rowTallestRight = 0
            row.forEachIndexed() { j, tree ->
                // Top Down
                if (i == 0 || j == 0) {
                    if (!tree.visible) {
                        tree.visible = true
                        visibleTrees++
                    }
                } else {
                    if (tree.height > rowTallestLeft  || tree.height > colTallestTop[j]) {
                        if (!tree.visible) {
                            tree.visible = true
                            visibleTrees++
                        }
                    }
                }
                if (tree.height > rowTallestLeft) rowTallestLeft = tree.height
                if (tree.height > colTallestTop[j]) colTallestTop[j] = tree.height

                // Bottom Up
                val iBot = forest.count() - 1 - i;
                val jBot = row.count() - 1 - j;
                val botRow = forest[iBot]
                val botTree = botRow[jBot]
                if (iBot == forest.count() - 1 || jBot == row.count() - 1) {
                    if (!botTree.visible) {
                        botTree.visible = true
                        visibleTrees++
                    }
                } else {
                    if (botTree.height > rowTallestRight || botTree.height > colTallestBot[jBot]) {
                        if (!botTree.visible) {
                            botTree.visible = true
                            visibleTrees++
                        }
                    }
                }
                if (botTree.height > rowTallestRight) rowTallestRight = botTree.height
                if (botTree.height > colTallestBot[jBot]) colTallestBot[jBot] = botTree.height
            }
        }
        return visibleTrees
    }

    fun calcNorth(startRow: Int, col: Int): Int {
        if (startRow == 0) return 0
        var score = 1
        val tree = forest[startRow][col]
        var row = startRow - 1
        while (row > 0) {
            if (forest[row][col].height >= tree.height) {
                break;
            }
            score++
            row--
        }
        return score
    }

    fun calcEast(row: Int, startCol: Int): Int {
        if (startCol == forest[row].count() - 1) return 0
        var score = 1
        val tree = forest[row][startCol]
        var col = startCol + 1
        while (col < forest[row].count() - 1) {
            if (forest[row][col].height >= tree.height) {
                break;
            }
            score++
            col++
        }
        return score
    }

    fun calcSouth(startRow: Int, col: Int): Int {
        if (startRow == forest.count()-1) return 0
        var score = 1
        val tree = forest[startRow][col]
        var row = startRow + 1
        while (row < forest.count() - 1) {
            if (forest[row][col].height >= tree.height) {
                break;
            }
            score++
            row++
        }
        return score
    }

    fun calcWest(row: Int, startCol: Int): Int {
        if (startCol == 0) return 0
        var score = 1
        val tree = forest[row][startCol]
        var col = startCol - 1
        while (col > 0) {
            if (forest[row][col].height >= tree.height) {
                break;
            }
            score++
            col--
        }
        return score
    }
    fun calcScenicScore(row: Int, col: Int): Int {
        return calcNorth(row, col) * calcEast(row, col) * calcSouth(row, col) * calcWest(row, col)
    }

    fun printScenicScore(row: Int, col: Int) {
        println("N:${calcNorth(row, col)} E:${calcEast(row, col)} S:${calcSouth(row, col)} W:${calcWest(row, col)}")
    }

    fun findHighestScenicScore(): Int {
        var highestScenicScore = 0
        var rowI = 0
        var colI = 0
        forest.forEachIndexed() { rowIndex, row ->
            row.forEachIndexed() { colIndex, tree ->
                val scenicScore = calcScenicScore(rowIndex, colIndex)
                if (scenicScore > highestScenicScore) {
                    highestScenicScore = scenicScore
                    rowI = rowIndex
                    colI = colIndex
                }
            }
        }
        println("${rowI}, ${colI}")
        return highestScenicScore
    }

    fun printVisibility() {
        forest.forEach {
            val string: String = it.joinToString { tree ->
                if (tree.visible) "1" else "0"
            }
            println(string)
        }
    }
}