package aoc2022

import java.io.File
class Day7 {
    private val fileSystem: Directory = Directory("/")
    private var pwd: Directory = fileSystem
    private var runningSum = 0

    class Directory(val name: String) {
        var parentDir: Directory? = null
        var fileSize: Int = 0;
        val children: MutableMap<String, Directory> = mutableMapOf()
        val files: MutableMap<String, Int> = mutableMapOf()
    }
    private fun processCommand(input: List<String>) {
        if (input[1] == "cd") {
            val dirName = input[2]
            if (dirName == "..") {
                if (pwd.parentDir != null) {
                    pwd = pwd.parentDir!!
                }
            } else if (dirName == "/") {
                pwd = fileSystem
            } else {
                val dir = pwd.children[dirName]
                if (dir == null) {
                    val newDir: Directory = Directory(dirName)
                    newDir.parentDir = pwd
                    pwd.children[dirName] = newDir
                    pwd = newDir
                }
            }
        }
    }

    private fun addFileToDir(d: Directory, file: Pair<String, Int>) {
        d.files.put(file.first, file.second)
        d.fileSize += file.second
        bubbleUpFileSize(d.parentDir, file.second)
    }

    init {
        File("src/main/resources/input/07.txt").forEachLine { line ->
            val cmd = line.split(" ")
            if (cmd[0] == "$") processCommand(cmd)
            else if (cmd[0][0].isDigit()) {
                val fileName = cmd[1]
                val fileSize = cmd[0].toInt()
                addFileToDir(pwd, Pair(fileName, fileSize))
            }
        }
    }

    private fun printDir(d: Directory) {
        println("${d.name} - ${d.parentDir?.name} - ${d.fileSize}")
        println(d.files)
        d.children.forEach() { printDir(it.value) }
    }

    fun printFileSystem() {
        printDir(fileSystem)
    }

    private fun bubbleUpFileSize(d: Directory?, size: Int) {
        if(d!= null) {
            d.fileSize += size
            bubbleUpFileSize(d.parentDir, size)
        }
    }

    fun getRunningSum() {
        findDirsUnder100KB(fileSystem)
        println(runningSum)
    }

    private fun findDirsUnder100KB(d: Directory) {
        if (d.fileSize <= 100000) {
            runningSum += d.fileSize
        }
        d.children.forEach{
            findDirsUnder100KB(it.value)
        }
    }

    private fun getAllDirSizes(d: Directory): List<Int> {
        val list = mutableListOf(d.fileSize)
        val childSizes = d.children.flatMap {
            getAllDirSizes(it.value)
        }
        return list + childSizes
    }

    fun findSmallestDeletableDirectory() {
        val freeSpace = 70000000 - fileSystem.fileSize
        val requiredSpace = 30000000 - freeSpace
        println(requiredSpace)
        var dirSizes = getAllDirSizes(fileSystem)
        dirSizes = dirSizes.sorted()
        val smallestPossible = dirSizes.find {
            it >= requiredSpace
        }
        print(smallestPossible)
    }
}