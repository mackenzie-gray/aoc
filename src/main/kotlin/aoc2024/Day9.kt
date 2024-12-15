package aoc2024

import aoc2024.Day9.Block
import lib.Puzzle
import kotlin.reflect.typeOf

typealias Disk = ArrayDeque<Block>

class Day9 : Puzzle() {
    var diskMap = ""

    override fun loadInput(fileName: String) {
        super.loadInput(fileName)
        diskMap = input[0]
    }

    open class Block(var id: Long, var size: Int = 0, var isEmptySpace: Boolean = false)
    class EmptyBlock(size: Int): Block(-1, size, true)

    fun loadDisk(): Disk {
        val disk = Disk()
        var emptySpace = false
        var blockId = 0L
        diskMap.forEach {
            if (emptySpace) {
                disk.add(EmptyBlock(it.digitToInt()))
            } else {
                disk.add(Block(blockId, it.digitToInt()))
                blockId++
            }
            emptySpace = !emptySpace
        }
        return disk
    }

    fun compressDiskP1(disk: Disk): Disk{
        return disk;
    }

    fun compressDiskP2(d: Disk): Disk {
        val tries = d.reversed().filterNot { it.isEmptySpace }
        var disk = d
        tries.forEach { block ->
            val index = disk.indexOf(block)
            val freeSpaceIndex = disk.indexOfFirst { it.isEmptySpace && it.size >= block.size }
            if (freeSpaceIndex in 0..<index) {
                disk.removeAt(index)
                disk.add(index, EmptyBlock(block.size))
                disk = insertIntoDisk(disk, block, freeSpaceIndex)
            }
        }
        return disk
    }

    fun insertIntoDisk(disk: Disk, block: Block, index: Int): Disk {
        disk[index].size -= block.size
        if (disk[index].size == 0) {
            disk.removeAt(index)
        }
        disk.add(index, block)
        return disk
    }

    fun computeChecksum(disk: Disk): Long {
        var index = 0
        return disk.fold(0L) { acc, block ->
            if (!block.isEmptySpace) {
                var r = 0L
                for (i in 1..block.size) {
                    r += block.id * index
                    index++
                }
                acc + r
            } else {
                index += block.size
                acc
            }
        }
    }

    override fun partA(): Long {
        var disk = loadDisk()
        disk = compressDiskP1(disk)
        return computeChecksum(disk)
    }

    override fun partB(): Long {
        var disk = loadDisk()
        disk = compressDiskP2(disk)
        return computeChecksum(disk)
    }
}