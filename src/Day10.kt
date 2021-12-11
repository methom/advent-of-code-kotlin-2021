fun main() {
    val dayNumber = "10"


    fun part1(input: List<String>): Int {

        var totalPenalty = 0
        input.forEach {
            val rowStack : Stack = mutableListOf()
            it.forEach { bracket ->
                when(bracket) {
                    BRACKETS.ROUND.opening -> rowStack.push(BRACKETS.ROUND)
                    BRACKETS.SQUARE.opening -> rowStack.push(BRACKETS.SQUARE)
                    BRACKETS.CURLY.opening -> rowStack.push(BRACKETS.CURLY)
                    BRACKETS.ANGLE.opening -> rowStack.push(BRACKETS.ANGLE)
                    BRACKETS.ROUND.closing -> {
                        if(rowStack.hasMore()) {
                            if(rowStack.pop()!!.closing != bracket) { totalPenalty += BRACKETS.ROUND.penalty }
                        }
                    }
                    BRACKETS.SQUARE.closing -> {
                        if(rowStack.hasMore()) {
                            if(rowStack.pop()!!.closing != bracket) { totalPenalty += BRACKETS.SQUARE.penalty }
                        }
                    }
                    BRACKETS.CURLY.closing -> {
                        if(rowStack.hasMore()) {
                            if(rowStack.pop()!!.closing != bracket) { totalPenalty += BRACKETS.CURLY.penalty }
                        }
                    }
                    BRACKETS.ANGLE.closing -> {
                        if(rowStack.hasMore()) {
                            if(rowStack.pop()!!.closing != bracket) { totalPenalty += BRACKETS.ANGLE.penalty }
                        }
                    }
                }
            }
        }
        return totalPenalty
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${dayNumber}_test")
    check(part1(testInput) == 26397)
    check(part2(testInput) == 10)

    val input = readInput("Day${dayNumber}")
    println("Part 1 answer: ${part1(input)}")
    println("Part 2 answer: ${part2(input)}")
}

enum class BRACKETS(val opening: Char, val closing: Char, val penalty: Int){
    ROUND('(', ')', 3),
    SQUARE('[', ']', 57),
    CURLY('{', '}', 1197),
    ANGLE('<', '>', 25137)


}
typealias Stack = MutableList<BRACKETS>
fun <T> MutableList<T>.push(item: T) = this.add(this.count(), item)
fun <T> MutableList<T>.pop(): T? = if(this.isNotEmpty()) this.removeAt(this.count() - 1) else null
fun <T> MutableList<T>.peek(): T? = if(this.isNotEmpty()) this[this.count() - 1] else null
fun <T> MutableList<T>.hasMore() = this.isNotEmpty()