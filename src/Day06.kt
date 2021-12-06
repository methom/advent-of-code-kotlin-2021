fun main() {
    val dayNumber = "06"

    fun calculateSwarmSizeAfterDays(initialSwarm: List<String>, days: Int): Long {
        val fishInitList = initialSwarm[0].split(',').map { it.toInt() }
        val swarm = LanternFishSwarm(fishInitList)
        swarm.grow(days = days)
        return swarm.size()
    }

    fun part1(input: List<String>): Long {
        return calculateSwarmSizeAfterDays(input, 80)
    }

    fun part2(input: List<String>): Long {
        return calculateSwarmSizeAfterDays(input, 256)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${dayNumber}_test")
    check(part1(testInput) == 5934L)
    check(part2(testInput) == 26984457539)

    val input = readInput("Day${dayNumber}")
    println("Part 1 answer: ${part1(input)}")
    println("Part 2 answer: ${part2(input)}")
}

class LanternFishSwarm(fish: List<Int>) {
    private val fishSwarm = mutableMapOf<Int, Long>()

    init {
        fish.forEach {
            fishSwarm.merge(it, 1) { a, b -> a + b }
        }
    }


    fun grow(days: Int) {
        var spawningFish: Long
        for (day in 0 until days) {
            spawningFish = fishSwarm.getOrDefault(0, 0)
            for (i in 0..7) {
                fishSwarm[i] = fishSwarm.getOrDefault((i + 1), 0)
            }
            fishSwarm.merge(6, spawningFish) { a, b -> a + b }
            fishSwarm[8] = spawningFish
        }
    }


    fun size(): Long {
        var fishSwarmSize = 0L
        fishSwarm.forEach {
            fishSwarmSize += it.value
        }
        return fishSwarmSize
    }

    fun printSwarm() {
        fishSwarm.toSortedMap().forEach{
            println("${it.key}:\t${it.value}")
        }
        println()
    }

}
