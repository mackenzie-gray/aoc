package days

import java.io.File

class Day1 {
    private val elves: ArrayList<Int> = countElfCalories();

    private fun countElfCalories(): ArrayList<Int> {
        var calorieCount = 0;
        val elves = ArrayList<Int>();
        File("src/main/resources/input/01.txt").useLines() {lines ->
            lines.forEach { count ->
                if (count != "") {
                    calorieCount += count.toInt();
                } else {
                    elves.add(calorieCount);
                    calorieCount = 0;
                }
            }
        }
        return elves;
    }

    fun problem1(): Int {
        val sortedElves: MutableList<Int> = elves.toMutableList();
        sortedElves.sortDescending()
        return sortedElves[0];
    }

    fun problem2(): Int {
        val sortedElves: MutableList<Int> = elves.toMutableList();
        sortedElves.sortDescending()
        return sortedElves[0] + sortedElves[1] + sortedElves[2];
    }
}