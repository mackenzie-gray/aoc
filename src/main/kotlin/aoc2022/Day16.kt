package aoc2022

import java.io.File

class Day16 {

    class Valve(
        var name: String,
        var rate: Int,
        var open: Boolean = false
    ) {
        var tunnels: MutableList<Valve> = mutableListOf();
    };

    var valves: MutableMap<String, Valve> = mutableMapOf();
    var tunnels: MutableMap<String, List<String>> = mutableMapOf();
    val travelCost = 1;
    val numMinutes = 30;

    init {
        val lines = readFile("src/main/resources/input/mini.txt")
        parseLines(lines);
        print(valves);
    }

    private fun parseLines(lines: List<String>) {
        val lineRegex = "Valve (\\w+) has flow rate=(\\d+); tunnels? leads? to valves? ((?:\\w+, )*\\w+)".toRegex()
        lines.forEach { line ->
            val matchResult = lineRegex.find(line)
            val name = matchResult!!.groups.get(1)!!.value;
            val rate = matchResult.groups.get(2)!!.value;
            val t = matchResult.groups.get(3)!!.value;
            valves[name] = Valve(name, rate.toInt());
            tunnels[name] = t.split(", ")
        }

        tunnels.forEach { v ->
            v.value.forEach {
                valves[v.key]!!.tunnels.add(valves[it]!!)
            }
        }
    }

    private fun readFile(file: String): List<String> {
        val fileObj = File(file)
        return fileObj.readLines()
    }
}