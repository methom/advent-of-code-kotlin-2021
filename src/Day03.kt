import java.util.*

fun main() {
    val dayNumber = "03"

    fun part1(input: List<String>): Long {

        val bitFieldBreadth = input[0].length
        val gamma = BitSet(bitFieldBreadth)
        val epsilon = BitSet(bitFieldBreadth)
        val threshold = input.size / 2
        val setBitCounterByIndex = IntArray(bitFieldBreadth)

        input.forEach {
            it.forEachIndexed { index, c: Char ->
                if (c == '1') {
                    setBitCounterByIndex[index]++
                }
            }
        }

        setBitCounterByIndex.forEachIndexed { index, i ->
            if (i > threshold) {
                gamma.set(index)
            } else {
                epsilon.set(index)
            }
        }

        val decimalGamma = gamma.switchEndianess(bitFieldBreadth).toLongArray()[0]
        val decimalEpsilon = epsilon.switchEndianess(bitFieldBreadth).toLongArray()[0]

        return decimalGamma * decimalEpsilon
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${dayNumber}_test")
    check(part1(testInput) == 198L)
    check(part2(testInput) == 12)

    val input = readInput("Day${dayNumber}")
    println(part1(input))
    println(part2(input))
}

private fun BitSet.switchEndianess(bitFieldBreadth: Int): BitSet {
    val returnSet = BitSet(bitFieldBreadth)
    for ( i in 0 until bitFieldBreadth) {
        returnSet[bitFieldBreadth - 1 - i] = this[i]
    }
    return returnSet
}
