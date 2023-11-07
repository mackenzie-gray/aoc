package days

import lib.Direction
import lib.Vector2
import java.io.File
class Day23 {

    private var checks: Map<Direction, List<Direction>> = mapOf(
        Direction.NORTH to listOf(Direction.NORTH, Direction.NORTHEAST, Direction.NORTHWEST),
        Direction.SOUTH to listOf(Direction.SOUTH, Direction.SOUTHEAST, Direction.SOUTHWEST),
        Direction.WEST to listOf(Direction.WEST, Direction.NORTHWEST, Direction.SOUTHWEST),
        Direction.EAST to listOf(Direction.EAST, Direction.NORTHEAST, Direction.SOUTHEAST)
    )
    var moveOrder = ArrayDeque<Direction>();

    private var elves =  mutableListOf<Vector2>();

    var y = 0;
    var x = 0;

    init {
        readFile("src/main/resources/input/23.txt")
        initMoves()
        var moves = 0;
        while(move()) {
            moves++;
        }
        println(getEmptyCells());
        print(moves)
    }

    private fun getEmptyCells(): Int {
        val xBounds = getBounds(Vector2.Axis.X);
        val yBounds = getBounds(Vector2.Axis.Y);
        println(xBounds);
        println(yBounds);
        return (Math.abs(xBounds.second - xBounds.first + 1) * Math.abs(yBounds.second - yBounds.first + 1)) - elves.size;
    }

    private fun getBounds(axis: Vector2.Axis): Pair<Int, Int> {
        var min = elves[0][axis].toInt();
        var max = elves[0][axis].toInt();

        elves.forEach {
            if (it[axis] < min) {
                min = it[axis].toInt()
            }
            if (it[axis] > max) {
                max = it[axis].toInt()
            }
        }

        return Pair(min, max);
    }

    private fun printGrid() {
        val (min, max) = getBounds(Vector2.Axis.Y);

        for (i in max downTo min - 1) {
            for (j in 0 until x) {
                val vec = Vector2(j, i);
                if (elves.contains(vec)) {
                    print("# ");
                } else {
                    print(". ")
                }

            }
            print("\n");
        }
        print("\n");
    }

    private fun initMoves() {
        moveOrder.addLast(Direction.NORTH);
        moveOrder.addLast(Direction.SOUTH);
        moveOrder.addLast(Direction.WEST);
        moveOrder.addLast(Direction.EAST);
    }

    private fun move(): Boolean {
        val queue = determineEligible();
        val plannedMoves = findMoves(queue);
        if (plannedMoves.isEmpty()) return false;
        performMoves(plannedMoves)
        moveOrder.addLast(moveOrder.removeFirst());
        return true;
    }

    private fun performMoves(plannedMoves: Map<Vector2, MutableList<Vector2>>) {
        plannedMoves.forEach {
            if (it.value.size == 1) {
                val old = it.value[0];
                elves.removeIf { elf ->
                    elf == old;
                }
                elves.add(it.key)
            }
        }
    }

    private fun findMoves(queue: MutableList<Vector2>): Map<Vector2, MutableList<Vector2>> {
        val result = mutableMapOf<Vector2, MutableList<Vector2>>()
        queue.forEach { elf ->
            val moveDir = moveOrder.find { dir ->
                checks[dir]!!.none {
                    elves.contains(elf.neighbours[it]);
                }
            }
            if (moveDir != null) {
                val move = elf.neighbours[moveDir]!!;
                if (result[move].isNullOrEmpty()) {
                    result[move] = mutableListOf(elf)
                } else {
                    result[move]?.add(elf);
                }
            }
        }
        return result;
    }

    private fun determineEligible(): MutableList<Vector2> = elves.filter { elf ->
        elf.neighbours.filter {
            elves.contains(it.value)
        }.isNotEmpty();
    }.toMutableList();

    private fun readFile(file: String) {
        val fileObj = File(file)

        fileObj.forEachLine { line ->
            line.toCharArray().forEachIndexed { idx, it ->
                val pos = Vector2(idx, y)
                if (it == '#') {
                    elves.add(pos);
                }
            }
            x = line.length
            y--;
        }
    }
}