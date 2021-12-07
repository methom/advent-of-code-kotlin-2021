import kotlin.math.abs
import kotlin.system.measureTimeMillis

fun main() {
    val dayNumber = "07"

    fun calculateMedian(positions: List<Int>): Int {
        val median = if (positions.size % 2 == 1) {
            positions[positions.size / 2]
        } else {
            (positions[(positions.size - 1) / 2] + positions[(positions.size + 1) / 2]) / 2
        }
        return median
    }

    fun part1(input: List<String>): Int {
        val positions = input[0].split(',').map { it.toInt() }.sorted()
        val median = calculateMedian(positions)
        var fuel = 0
        positions.forEach { fuel += abs(it - median) }
        return fuel
    }

    fun calculateFuelCostToCandidate(position: Int, target: Int): Int {
        val distance = abs(target - position)
        var fuelConsumption = 0
        for (i in 1..distance) {
            fuelConsumption += i
        }
        return fuelConsumption
    }

    fun part2(input: List<String>): Int {
        val positions = input[0].split(',').map { it.toInt() }.sorted()
        val fuelCostPerMedianCandidate = mutableMapOf<Int, Int>()
        for (candidate in positions[0]..positions[positions.size - 1]) {
            var candidateFuelCost = 0
            positions.forEach {
                candidateFuelCost += calculateFuelCostToCandidate(it, candidate)
            }
            fuelCostPerMedianCandidate[candidate] = candidateFuelCost
        }
        return fuelCostPerMedianCandidate.values.sorted()[0]
    }

    fun partOpt2(input: List<String>): Int {
        val positions = input[0].split(',').map { it.toInt() }.sorted()
        val median = calculateMedian(positions)

        // getCostToGetToMedian
        val fuelCostToMedian = positions.sumOf { calculateFuelCostToCandidate(it, median) }

        // getCostToGetToOneAfterMedian
        val fuelCostToPostMedian = positions.sumOf { calculateFuelCostToCandidate(it, median + 1) }

        // getCostToGetToOneBeforeMedian
        val fuelCostToPreMedian = positions.sumOf { calculateFuelCostToCandidate(it, median - 1) }

        // identifyDescentDirection
        if (fuelCostToPreMedian < fuelCostToMedian) {
            // as long as decent continues, follow
            var previousCost: Int
            var currentCost = fuelCostToPreMedian
            for (candidate in median - 2 downTo positions[0]) {
                previousCost = currentCost
                currentCost = positions.sumOf { calculateFuelCostToCandidate(it, candidate) }
                if (currentCost > previousCost) {
                    return previousCost
                }
            }
        } else if (fuelCostToMedian > fuelCostToPostMedian) {
            var previousCost: Int
            var currentCost = fuelCostToPostMedian
            for (candidate in median + 2..positions[positions.size - 1]) {
                previousCost = currentCost
                currentCost = positions.sumOf { calculateFuelCostToCandidate(it, candidate) }
                if (currentCost > previousCost) {
                    return previousCost
                }
            }
        }

        println("We shouldn't be here!")
        return part2(input)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${dayNumber}_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)
    check(partOpt2(testInput) == 168)

    val input = readInput("Day${dayNumber}")
    var timeInMillis = measureTimeMillis { println("Part 1 answer: ${part1(input)}") }
    println("Part 1 took $timeInMillis ms")
    timeInMillis = measureTimeMillis { println("Part 2 answer: ${part2(input)}") }
    println("Part 2 took $timeInMillis ms")
    timeInMillis = measureTimeMillis { println("Part 2 optimized answer: ${partOpt2(input)}") }
    println("Part 2 optimized took $timeInMillis ms")
}
