import java.util.*

fun main() {
    val dayNumber = "03"

    fun findMostCommonBitPerPosition(input: List<String>): IntArray {
        val setBitCounterByIndex = IntArray(input[0].length)
        input.forEach {
            it.forEachIndexed { index, c: Char ->
                if (c == '1') {
                    setBitCounterByIndex[index]++
                }
            }
        }
        return setBitCounterByIndex
    }

    fun part1(input: List<String>): Long {

        val bitFieldBreadth = input[0].length
        val gamma = BitSet(bitFieldBreadth)
        val threshold = input.size / 2

        val setBitCounterByIndex = findMostCommonBitPerPosition(input)

        setBitCounterByIndex.forEachIndexed { index, i ->
            if (i > threshold) {
                gamma.set(index)
            }
        }

        val epsilon = gamma.invert(bitFieldBreadth)

        val decimalGamma = gamma.switchEndianess(bitFieldBreadth).toLongArray()[0]
        val decimalEpsilon = epsilon.switchEndianess(bitFieldBreadth).toLongArray()[0]

        return decimalGamma * decimalEpsilon
    }

    fun part2(input: List<String>): Long {
        val bitFieldBreadth = input[0].length
        val oxygenGeneratorRating = BitSet(bitFieldBreadth)
        val co2ScrubberRating = BitSet(bitFieldBreadth)

        val filtered = mutableListOf<String>()
        filtered.addAll(input)
        var candidates: MutableList<String>
        var index = 0
        while (filtered.size > 1) {
            val numberOfSetBits = filtered.getAmountOfCharAtIndex('1', index)
            val majorityBit = if(2*numberOfSetBits >= filtered.size) '1' else '0'
            candidates = filtered.filterForCharAtIndex(majorityBit, index)
            filtered.clear()
            filtered.addAll(candidates)
            candidates.clear()
            index++
        }
        for ( i in 0 until bitFieldBreadth) {
            if(filtered[0][i].digitToInt()==1) oxygenGeneratorRating.set(i)
        }
        val decimalOxygenGeneratorRating = oxygenGeneratorRating.switchEndianess(bitFieldBreadth).toLongArray()[0]

        filtered.clear()
        filtered.addAll(input)
        index = 0
        while (filtered.size > 1) {
            val numberOfUnsetBits = filtered.getAmountOfCharAtIndex('0', index)
            val numberOfSetBits = filtered.getAmountOfCharAtIndex('1', index)
            val minorityBit = if(numberOfUnsetBits <= numberOfSetBits) '0' else '1'
            candidates = filtered.filterForCharAtIndex(minorityBit, index)
            filtered.clear()
            filtered.addAll(candidates)
            candidates.clear()
            index++
        }
        for ( i in 0 until bitFieldBreadth) {
            if(filtered[0][i].digitToInt()==1) co2ScrubberRating.set(i)
        }


        val decimalCo2ScrubberRating = co2ScrubberRating.switchEndianess(bitFieldBreadth).toLongArray()[0]
        return decimalCo2ScrubberRating * decimalOxygenGeneratorRating
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${dayNumber}_test")
    check(part1(testInput) == 198L)
    check(part2(testInput) == 230L)

    val input = readInput("Day${dayNumber}")
    println("Part 1 answer: ${part1(input)}")
    println("Part 2 answer: ${part2(input)}")
}

private fun <String> List<String>.getAmountOfCharAtIndex(char: Char, index: Int): Int {
    var counter = 0
    this.forEach {
        if(it.toString()[index] == char) counter++
    }
    return counter
}

private fun <String> List<String>.filterForCharAtIndex(char: Char, index: Int): MutableList<String> {
    val returnList = mutableListOf<String>()
    this.forEach {
        if(it.toString()[index] == char) returnList.add(it)
    }
    return returnList
}

private fun <String> List<String>.getMajorityCharAtIndex(i: Int): Char {
    val charDistribution = mutableMapOf<Char, Int>()
    this.forEach {
        val char = it.toString()[i]
        charDistribution.merge(char, 1, Int::plus)
        }
    val majority = charDistribution.maxByOrNull { it.value }
    return majority!!.key
}

private fun BitSet.invert(bitFieldBreadth: Int): BitSet {
    val returnSet = BitSet(bitFieldBreadth)
    for ( i in 0 until bitFieldBreadth) {
        returnSet[i] = !this[i]
    }
    return returnSet
}

private fun BitSet.switchEndianess(bitFieldBreadth: Int): BitSet {
    val returnSet = BitSet(bitFieldBreadth)
    for ( i in 0 until bitFieldBreadth) {
        returnSet[bitFieldBreadth - 1 - i] = this[i]
    }
    return returnSet
}
