fun main() {
    val dayNumber = "01"

    fun part1(input: List<String>): Int {
        return (1 until input.size).count { input[it].toInt() > input[it - 1].toInt() }
    }

    fun part2(input: List<String>): Int {
        return (3 until input.size).count { (input[it].toInt() + input[it - 1].toInt() + input[it - 2].toInt()) > (input[it - 1].toInt() + input[it - 2].toInt() + input[it - 3].toInt()) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${dayNumber}_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day${dayNumber}")
    println(part1(input))
    println(part2(input))
}