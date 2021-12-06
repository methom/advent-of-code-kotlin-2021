fun main() {
    val dayNumber = "06"

    fun part1(input: List<String>): Int {
        val fishInitList = input[0].split(',').map { it.toInt() }
        val swarm = LanternFishSwarm(fishInitList)
        swarm.grow(days = 80)
        return swarm.size()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${dayNumber}_test")
    check(part1(testInput) == 5934)
    check(part2(testInput) == 1)

    val input = readInput("Day${dayNumber}")
    println("Part 1 answer: ${part1(input)}")
    println("Part 2 answer: ${part2(input)}")
}

class LanternFishSwarm(fish: List<Int>) {
    val fishSwarm = mutableListOf<LanternFish>()

    init {
        fish.forEach{ fishSwarm.add(LanternFish(it)) }
    }

    class LanternFish(var daysUntilReplication: Int = 8) {
        fun age(): Boolean {
            var hasSpawned = false
            if (daysUntilReplication == 0) {
                daysUntilReplication = 7
                hasSpawned = true
            }
            daysUntilReplication--
            return hasSpawned
        }
    }

    fun printPopulation() {
        fishSwarm.forEach { print("${it.daysUntilReplication},") }
        println()
    }

    fun grow(days: Int) {
        var fishSpawnCounter = 0
        for(day in 0 until days) {
            for (i in 0 until 1) {
                fishSwarm.forEach {
                    if(it.age()) { fishSpawnCounter++ } }
                for (j in 0 until fishSpawnCounter) {
                    fishSwarm.add(LanternFish())
                }
                fishSpawnCounter = 0
            }
        }
    }

    fun size(): Int {
        return fishSwarm.size
    }

}
