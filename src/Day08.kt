fun main() {
    val dayNumber = "08"

    fun part1(input: List<String>): Int {
        val data = mutableListOf<DataSet>()
        input.forEach {
            data.add(DataSet(it.split('|')[0], it.split('|')[1]))
        }
        var simpleDigitCounter = 0
        data.forEach {
            simpleDigitCounter += it.second.split(' ').count { s -> s.length == 2 || s.length == 3 || s.length == 4 || s.length == 7 }
        }
        return simpleDigitCounter
    }


    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${dayNumber}_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 10)

    val input = readInput("Day${dayNumber}")
    println("Part 1 answer: ${part1(input)}")
    println("Part 2 answer: ${part2(input)}")
}

typealias DataSet = Pair<String, String>
