import kotlin.math.abs

fun main() {
    val dayNumber = "07"

    fun part1(input: List<String>): Int {
        val positions = input[0].split(',').map { it.toInt() }.sorted()
        val median = if(input.size%2 == 1) {
            positions[positions.size/2]
        } else {
            (positions[(positions.size - 1)/2] + positions[(positions.size + 1)/2])/2
        }
        var fuel = 0
        positions.forEach { fuel += abs(it - median) }
        return fuel
    }

    fun calculateFuelCostToCandidate(position: Int, target: Int): Int {
        val distance = abs(target - position)
        var fuelConsumption = 0
        for(i in 1 .. distance) {
            fuelConsumption += i
        }
        return fuelConsumption
    }

    fun part2(input: List<String>): Int {
        val positions = input[0].split(',').map { it.toInt() }.sorted()
        val fuelCostPerMedianCandidate = mutableMapOf<Int, Int>()
        for(candidate in positions[0] .. positions[positions.size - 1]) {
            var candidateFuelCost = 0
            positions.forEach {
                candidateFuelCost += calculateFuelCostToCandidate(it, candidate)
            }
            fuelCostPerMedianCandidate[candidate] = candidateFuelCost
        }
        return fuelCostPerMedianCandidate.values.sorted()[0]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${dayNumber}_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day${dayNumber}")
    println("Part 1 answer: ${part1(input)}")
    println("Part 2 answer: ${part2(input)}")
}
