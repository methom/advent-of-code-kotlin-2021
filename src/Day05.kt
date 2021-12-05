import java.lang.Integer.max
import java.lang.Integer.min

fun main() {
    val dayNumber = "05"

    fun part1(input: List<String>): Int {
        val ventMap = CoordMap()
        val regex = "\\D+".toRegex()
        input.forEach {
            val coords = it.split(regex)
            ventMap.addVentLine(coords)
        }
        return ventMap.countMostDangerousAreas()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${dayNumber}_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 10)

    val input = readInput("Day${dayNumber}")
    println("Part 1 answer: ${part1(input)}")
    println("Part 2 answer: ${part2(input)}")
}

class CoordMap() {

    private var maxY: Int = 0
    private var maxX: Int = 0
    private val ventLineList = mutableListOf<VentLine>()

    class VentLine(coords: List<String>) {
        val startingPoint = Pair(coords[0].toInt(), coords[1].toInt())
        val endingPoint = Pair(coords[2].toInt(), coords[3].toInt())
    }

    fun addVentLine(coords: List<String>) {
        ventLineList.add(VentLine(coords))
        updateMaxMapCoordinates(coords)
    }

    private fun updateMaxMapCoordinates(coords: List<String>) {
        if (max(coords[0].toInt(), coords[2].toInt()) > maxX) maxX = max(coords[0].toInt(), coords[2].toInt())
        if (max(coords[1].toInt(), coords[3].toInt()) > maxY) maxY = max(coords[1].toInt(), coords[3].toInt())
    }

    fun printLineList() {
        ventLineList.forEach {
            println("${it.startingPoint.first},${it.startingPoint.second} -> ${it.endingPoint.first},${it.endingPoint.second}")
        }
    }

    fun printMap() {
        val map = initMap()
        map.forEach {
            it.forEach { value ->
                print(if (value == 0) ". " else "$value ")
            }
            println()
        }
    }

    private fun initMap(): Array<IntArray> {
        val map = Array(maxY + 1) { IntArray(maxX + 1) { 0 } }
        ventLineList.forEach {
            if (it.startingPoint.first == it.endingPoint.first) {
                // draw vertical line
                val x = it.startingPoint.first
                val start = min(it.startingPoint.second, it.endingPoint.second)
                val end = max(it.startingPoint.second, it.endingPoint.second)
                for (y in start..end) {
                    map[y][x]++
                }
            } else if (it.startingPoint.second == it.endingPoint.second) {
                // draw horizontal line
                val y = it.startingPoint.second
                val start = min(it.startingPoint.first, it.endingPoint.first)
                val end = max(it.startingPoint.first, it.endingPoint.first)
                for (x in start..end) {
                    map[y][x]++
                }
            }
        }
        return map
    }

    fun countMostDangerousAreas(): Int {
        val map = initMap()
        var numberOfDangerousAreas = 0
        map.forEach {
            it.forEach { value ->
                if (value > 1) {
                    numberOfDangerousAreas++
                }
            }
        }
        return numberOfDangerousAreas
    }

}
