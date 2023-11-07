package aoc2021

import lib.Direction
import lib.Vector2
import java.io.File
import java.math.BigDecimal

class Day3(private var inputFileName: String) {
    private var input: List<String> = File(inputFileName).readLines();

    fun partA(): Int {
        var digitCounts = getDigitCounts(input);
        val epsRate = Integer.parseInt(getEpsilonRate(digitCounts), 2);
        val gammaRate = Integer.parseInt(getGammaRate(getEpsilonRate(digitCounts)), 2);

        return epsRate * gammaRate;
    }

    fun partB(): Int {
        val c02ScrubberRating = getC0ScrubberRating(input, 0);
        val oxygenGenRating = getO2GeneratorRating(input, 0);

        return Integer.parseInt(c02ScrubberRating, 2) * Integer.parseInt(oxygenGenRating, 2)
    }
    private fun getEpsilonRate(digitCounts: List<Map<String, Int>>): String {
        val er: List<Char> = digitCounts.map {
            if (it["0"]!! > it["1"]!!) {
                '0'
            } else {
                '1'
            }
        }
        return er.joinToString("");
    }

    private fun getGammaRate(epsRate: String): String {
        return epsRate.map {
            if (it == '0') '1'
            else '0'
        }.joinToString("")
    }

    private fun getO2GeneratorRating(lines: List<String>, bit: Int): String {
        var digitCounts = getDigitCounts(lines);
        var epsRate = getEpsilonRate(digitCounts);
        var filteredList = lines.filter {
            it[bit] == epsRate[bit]
        }
        if (filteredList.size == 1) return filteredList[0];
        else return getO2GeneratorRating(filteredList, bit + 1)
    }

    private fun getC0ScrubberRating(lines: List<String>, bit: Int): String {
        var digitCounts = getDigitCounts(lines);
        var epsRate = getEpsilonRate(digitCounts);
        var gammaRate = getGammaRate(epsRate)
        var filteredList = lines.filter {
            it[bit] == gammaRate[bit]
        }
        if (filteredList.size == 1) return filteredList[0];
        else return getC0ScrubberRating(filteredList, bit + 1)
    }

    private fun getDigitCounts(lines: List<String>): MutableList<MutableMap<String, Int>> {
        val numDigits = lines[0].length;
        val digitCounts: MutableList<MutableMap<String, Int>> = MutableList(numDigits) {
            mutableMapOf("1" to 0, "0" to 0)
        };
        lines.forEach() {
            it.forEachIndexed { index, c ->
                if (c == '0') {
                    digitCounts[index]["0"] = digitCounts[index]["0"]!! + 1
                } else {
                    digitCounts[index]["1"] = digitCounts[index]["1"]!! + 1
                }
            }
        }
        return digitCounts
    }
}