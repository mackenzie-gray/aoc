package aoc2022

import java.io.File
import kotlin.collections.ArrayList

typealias Compartments = Pair<IntArray, IntArray>

class Day3 {

    var rucksacks = ArrayList<Compartments>();
    var commonItemWeights = ArrayList<Int>();

    companion object {
        fun mapAsciiValueToWeight(item: Int): Int {
            return if (item > 96) item - 96 else item - 38
        }
    }

    init {
        File("src/main/resources/input/03.txt").useLines() { lines ->
            lines.forEach { l ->
                val compartments = Pair<String, String>(
                    l.substring(0,l.length/2),
                    l.substring(l.length/2)
                );

                val sack1 = compartments.first.chars().map {
                    mapAsciiValueToWeight(it)
                }.toArray();

                val sack2 = compartments.second.chars().map {
                    mapAsciiValueToWeight(it)
                }.toArray();

                rucksacks.add(Pair(sack1, sack2))
                val intersect = sack1.toSet().intersect(sack2.toSet())
                commonItemWeights.add(intersect.first());
            }
        }
        val sum = commonItemWeights.fold(0) { acc, i ->
            acc + i
        }
        println(sum)
    }

    fun getCommonBadges() {
        var commonBadgeWeightCount = 0;
        var i = 0
        while(i < rucksacks.count()) {
            val elf1 = rucksacks[i].first + rucksacks[i].second
            val elf2 = rucksacks[i+1].first + rucksacks[i+1].second
            val elf3 = rucksacks[i+2].first + rucksacks[i+2].second
            val intersect = elf1.toSet().intersect(elf2.toSet()).intersect(elf3.toSet())
            commonBadgeWeightCount += intersect.first();
            i += 3
        }

        print(commonBadgeWeightCount);
    }

}