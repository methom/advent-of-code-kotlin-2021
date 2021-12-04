fun main() {
    val dayNumber = "04"

    fun part1(input: List<String>): Int {
        val draws = input[0].split(',').map { it -> it.toInt() }
        val bingoCards = mutableListOf<BingoCard>()
        for (i in 1 until input.size) {
            if (input[i] == "") {
                bingoCards.add(BingoCard())
            } else {
                bingoCards.last().addLine(input[i])
            }
        }
        draws.forEach { draw ->
            bingoCards.forEach { if(it.draw(draw)) return it.winningValue(draw) }
        }

        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${dayNumber}_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 19)

    val input = readInput("Day${dayNumber}")
    println("Part 1 answer: ${part1(input)}")
    println("Part 2 answer: ${part2(input)}")
}

class BingoCard {
    private class Field(val value: Int, var drawn: Boolean = false) {
    }

    private val lines = mutableListOf<MutableList<Field>>()

    fun draw(drawnValue: Int) : Boolean {
        lines.forEach {
            it.forEach { field ->
                if (field.value == drawnValue) field.drawn = true
            }
        }
        return checkForBingo()
    }

    private fun checkForBingo(): Boolean {
        return checkHorizontalWins() || checkVerticalWins()
    }

    private fun checkVerticalWins(): Boolean {
        for(index in 0 until lines[0].size) {
            var lineDrawCounter = 0
            lines.forEach {
                if(it[index].drawn) lineDrawCounter++
            }
            if(lineDrawCounter == lines.size) return true
        }
        return false
    }

    private fun checkHorizontalWins(): Boolean {
        lines.forEach {
            if(it.count { field -> field.drawn } == it.count()) return true
        }
        return false
    }

    fun print() {
        lines.forEach {
            it.forEach { field ->
                if (field.drawn) {
                    print("XX ")
                } else {
                    print("%2d ".format(field.value))
                }
            }
            println()
        }
        println()
    }

    fun addLine(line: String) {
        val lineValues = line.trim().replace("  ", " ").split(' ')
        val fieldList = mutableListOf<Field>()
        lineValues.forEach {
            fieldList.add(Field(it.trim().toInt()))
        }
        lines.add(fieldList)
    }

    fun winningValue(winningDraw: Int): Int {
        return sumOfUndrawnFields() * winningDraw
    }

    private fun sumOfUndrawnFields(): Int {
        var fieldSum = 0
        for(index in 0 until lines[0].size) {
            lines.forEach {
                if(!it[index].drawn) fieldSum += it[index].value
            }
        }
        return fieldSum
    }
}
