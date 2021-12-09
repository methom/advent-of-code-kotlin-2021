fun main() {
    val dayNumber = "09"

    fun part1(input: List<String>): Int {
        val matrix = mutableListOf<List<Int>>()
        input.forEach {
            val row = it.map { v -> v.digitToInt() }
            matrix.add(row)
        }
        val localMinima = mutableListOf<Int>()
        matrix.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, i ->
                var lowerThanLeft = true
                if(colIndex > 0) {
                    lowerThanLeft = i < row[colIndex - 1]
                }
                var lowerThanTop = true
                if(rowIndex > 0) {
                    lowerThanTop = i < matrix[rowIndex - 1][colIndex]
                }
                var lowerThanRight = true
                if(colIndex < row.size - 1) {
                    lowerThanRight = i < row[colIndex + 1]
                }
                var lowerThanBottom = true
                if(rowIndex < matrix.size - 1) {
                    lowerThanBottom = i < matrix[rowIndex + 1][colIndex]
                }
                if (lowerThanLeft && lowerThanBottom && lowerThanRight && lowerThanTop) { localMinima.add(i) }
            }
        }
        return localMinima.sum() + localMinima.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${dayNumber}_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 5)

    val input = readInput("Day${dayNumber}")
    println("Part 1 answer: ${part1(input)}")
    println("Part 2 answer: ${part2(input)}")
}
