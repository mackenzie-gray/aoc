package days

import lib.Vector3
import java.io.File
import java.util.*

class Day18 {

    var cubeStack: Stack<Vector3> = Stack<Vector3>();
    var sideCounter: MutableMap<Vector3, Int> = mutableMapOf();
    var faces: MutableList<Vector3> = arrayListOf();

    var checkedCells: MutableMap<Vector3, Boolean> = mutableMapOf();

    var xMax = 0f;
    var yMax = 0f;
    var zMax = 0f;

    var surfaceArea = 0;

    init {
        readFile("src/main/resources/input/18.txt");
        airSearch(Vector3(-1f, -1f, -1f));
        println(surfaceArea)
    }

    private fun getAllSidesToCheck(vec: Vector3): Array<Vector3> {
        return arrayOf(
            Vector3(vec.x + 1, vec.y, vec.z),
            Vector3(vec.x - 1, vec.y, vec.z),
            Vector3(vec.x, vec.y + 1, vec.z),
            Vector3(vec.x, vec.y - 1, vec.z),
            Vector3(vec.x, vec.y, vec.z + 1),
            Vector3(vec.x, vec.y, vec.z - 1),
        );
    }

    private fun processSides() {
        while(cubeStack.size > 0) {
            val vec = cubeStack.pop();
            val sides = getAllSidesToCheck(vec);
            sides.forEach {
                if (sideCounter[it] != null) {
                    val diff = (it - vec) * 0.5f;
                    val face = diff + vec;
                    if (!faces.contains(face)) {
                        faces.add(face)
                        sideCounter[it] = sideCounter[it]!! - 1;
                        sideCounter[vec] = sideCounter[vec]!! - 1;
                    }
                }
            }
        }
        var openFaces = 0;
        sideCounter.forEach {
            openFaces += it.value
        }
        println(openFaces)
    }

    private fun isInBounds(cell: Vector3): Boolean {
        return cell.x >= -1 &&
            cell.y >= -1 &&
            cell.z >= -1 &&
            cell.x <= xMax + 1 &&
            cell.y <= yMax + 1 &&
            cell.z <= zMax + 1;
    }
    private fun airSearch(vec: Vector3) {
        checkedCells[vec] = true;
        var sides = getAllSidesToCheck(vec);
        sides.forEach { side ->
            if (checkedCells[side] != true && isInBounds(side)) {
                if (sideCounter[side] != null) {
                    surfaceArea += 1;
                } else {
                    airSearch(side);
                }
            }
        }
    }

    private fun readFile(file: String) {
        File(file).forEachLine { line ->
            val raw = line.split(',').map {
                it.toFloat();
            }
            var x = raw[0];
            var y = raw[1];
            var z = raw[2];

            if (x > xMax) xMax = x;
            if (y > yMax) yMax = y;
            if (z > zMax) zMax = z;

            val vector = Vector3(raw[0], raw[1], raw[2]);

            cubeStack.push(vector);
            sideCounter[vector] = 6;
        }
    }
}