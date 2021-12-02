fun main() {
    val dayNumber = "02"

    data class Location(var horizontal: Int = 0, var depth: Int = 0)


    fun part1(input: List<String>): Int {
        val location = Location()
        input.forEach {
            when(Direction.valueOf(it.split(' ')[0].uppercase())) {
                Direction.UP -> location.depth -= it.split(' ')[1].toInt()
                Direction.DOWN -> location.depth += it.split(' ')[1].toInt()
                Direction.FORWARD -> location.horizontal += it.split(' ')[1].toInt()
            }
        }
        return location.horizontal * location.depth
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${dayNumber}_test")
    check(part1(testInput) == 150)

    val input = readInput("Day${dayNumber}")
    println(part1(input))
    println(part2(input))
}

enum class Direction {
    FORWARD, DOWN, UP
}