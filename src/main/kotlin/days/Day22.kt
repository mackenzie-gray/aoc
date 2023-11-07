package days

import java.io.File

class Day22 {

    enum class Direction {
        U, D, L, R
    }

    class Coordinate(val x: Int, val y: Int) {
        override fun hashCode(): Int {
            var result = x
            result = 31 * result + y
            return result
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Coordinate

            if (x != other.x) return false
            return y == other.y
        }
    }

    private var map: MutableList<MutableList<String>> = mutableListOf()
    private var mapComplete = false
    private var moves: List<String> = mutableListOf()

    private var currentPosition: Coordinate
    private var currentDirection = Direction.R
    private var positionsTravelled: MutableMap<Coordinate, Direction> = mutableMapOf()

    init {
        val input = File("src/main/resources/input/22.txt").forEachLine { line ->
            if (mapComplete) {
                moves = line.split(Regex("(?<=\\D)|(?=\\D)"))
            }
            if (line.isEmpty()) {
                mapComplete = true
            }
            if (!mapComplete) {
                val mapRow = line.split("")
                map.add(mapRow.filter {
                    it != ""
                }.toMutableList())
            }
        }

        val startingPosition = map[0].indexOf(".")
        currentPosition = Coordinate(startingPosition, 0)

        moves.forEachIndexed() { i, it ->
            move(it)
        }

        print(getPasscode())
    }

    fun getPasscode(): Int {
        val facing = when(currentDirection) {
            Direction.R -> 0
            Direction.D -> 1
            Direction.L -> 2
            Direction.U -> 3
        }

        val col = currentPosition.x + 1
        val row = currentPosition.y + 1

        return (1000 * row) + (4 * col) + facing
    }

    fun step() {
        var nextPos = when(currentDirection){
            Direction.D -> Coordinate(currentPosition.x, currentPosition.y + 1)
            Direction.U -> Coordinate(currentPosition.x, currentPosition.y - 1)
            Direction.R -> Coordinate(currentPosition.x + 1, currentPosition.y)
            Direction.L -> Coordinate(currentPosition.x - 1, currentPosition.y)
        }

        if(isOOB(nextPos)) {
            nextPos = wrap()
        }

        if (map[nextPos.y][nextPos.x] == ".") {
            currentPosition = nextPos
            positionsTravelled[currentPosition] = currentDirection
        }
    }

    fun isOOB(pos: Coordinate): Boolean {
        return when (currentDirection) {
            Direction.U -> {
                pos.y <= 0 || pos.x >= map[pos.y].size || map[pos.y][pos.x].trim() == ""
            }
            Direction.D -> {
                pos.y >= map.size || pos.x >= map[pos.y].size || map[pos.y][pos.x].trim() == ""
            }
            Direction.R -> {
                pos.x >= map[pos.y].size || map[pos.y][pos.x].trim() == ""
            }
            Direction.L -> {
                pos.x <= 0 || map[pos.y][pos.x].trim() == ""
            }
        }
    }

    fun wrap(): Coordinate {
        when(currentDirection) {
            Direction.D -> {
                for (i in 0..map.count()) {
                    if (map[i][currentPosition.x].trim() != "") {
                        return Coordinate(currentPosition.x, i)
                    }
                }
            }
            Direction.U -> {
                for (i in map.count()-1..0) {
                    if (map[i][currentPosition.x].trim() != "") {
                        return Coordinate(currentPosition.x, i)
                    }
                }
            }
            Direction.R -> {
                for (i in 0..map[currentPosition.y].count()) {
                    if (map[currentPosition.y][i].trim() != "") {
                        return Coordinate(i, currentPosition.y)
                    }
                }
            }
            Direction.L -> {
                for (i in map[currentPosition.y].count()-1..0) {
                    if (map[currentPosition.y][i].trim() != "") {
                        return Coordinate(i, currentPosition.y)
                    }
                }
            }
        }
        return currentPosition
    }

    fun turnRight() {
        currentDirection = when(currentDirection) {
            Direction.D -> Direction.L
            Direction.U -> Direction.R
            Direction.R -> Direction.D
            Direction.L -> Direction.U
        }
        positionsTravelled[currentPosition] = currentDirection
    }

    fun turnLeft() {
        currentDirection = when(currentDirection) {
            Direction.D -> Direction.R
            Direction.U -> Direction.L
            Direction.R -> Direction.U
            Direction.L -> Direction.D
        }
        positionsTravelled[currentPosition] = currentDirection
    }
    fun move(move: String) {
        when(move) {
            "L" -> turnLeft()
            "R" -> turnRight()
            else -> {
                val moveCount = move.toInt()
                for(i in 1..moveCount) {
                    step()
                }
            }
        }
    }

    fun printPlayer(direction: Direction) {
        return when(direction) {
            Direction.D -> print("v")
            Direction.U -> print("^")
            Direction.L -> print("<")
            Direction.R -> print(">")
        }
    }
    fun printMap() {
        map.forEachIndexed { y, row ->
            row.forEachIndexed { x, col ->
                val coord = Coordinate(x,y)
                val dir = positionsTravelled[coord]
                if (dir != null) {
                    printPlayer(dir)
                } else {
                    print(col)
                }
            }
            print('\n')
        }
        print('\n')
    }


}