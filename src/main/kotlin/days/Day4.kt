package days

import java.io.File

class Day4 {
    init {
        var fullOverlapCount = 0
        var partialOverlapCount = 0

        File("src/main/resources/input/04.txt").useLines() { lines ->
            lines.forEach { l ->
                val assignments = l.split(",")

                val sectors1 = assignments[0].split("-");
                val sectors2 = assignments[1].split("-");

                val range1 = sectors1[0].toInt() ..sectors1[1].toInt()
                val sectorAssignment1: MutableSet<Int> = mutableSetOf();
                for(i in range1) {
                    sectorAssignment1.add(i)
                }

                val range2 = sectors2[0].toInt() ..sectors2[1].toInt()
                val sectorAssignment2: MutableSet<Int> = mutableSetOf();
                for(i in range2) {
                    sectorAssignment2.add(i)
                }

                val overlap = sectorAssignment1.intersect(sectorAssignment2);

                if (overlap.isNotEmpty()) partialOverlapCount++

                val sectorsFullyOverlap = overlap == sectorAssignment1 || overlap == sectorAssignment2
                if (sectorsFullyOverlap) {
                    fullOverlapCount++
                }
            }
        }

        println("Full Overlap: $fullOverlapCount")
        println("Partial Overlap: $partialOverlapCount")
    }
}