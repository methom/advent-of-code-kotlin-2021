fun main() {
    val dayNumber = "00"

    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${dayNumber}_test")
    check(part1(testInput) == 1)

    val input = readInput("Day${dayNumber}")
    println(part1(input))
    println(part2(input))
}