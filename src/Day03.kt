fun main() {
	fun part1(input: List<String>): Int {
		var counter = 0
		val bits = Array(12) { 0 }
		input.filterNot { it.isEmpty() }.forEach {
			it.forEachIndexed { index, char ->
				bits[index] += char.digitToInt()
			}
			counter++
		}
		val gammaRate = bits.joinToString("") { if (it * 2 > counter) "1" else "0" }.toInt(2)
		val epsilonRate = bits.joinToString("") { if (it * 2 > counter) "0" else "1" }.toInt(2)
		return gammaRate * epsilonRate
	}

	fun part2(input: List<String>): Int {
		fun getNumber(char: Char): String {
			var bit = 0
			val unwantedChar = if (char == '1') '0' else '1'
			var data = input.filterNot { it.isEmpty() }.groupBy { it[0] }
			while (data.values.flatten().size != 1) {
				val wanted = data[char]?.size ?: 0
				val unwanted = data[unwantedChar]?.size ?: 0
				val mostCommonChar: Char = if (
					when (char) {
						'0' -> wanted > unwanted
						'1' -> wanted >= unwanted
						else -> throw IllegalArgumentException("Unknown char: $char")
					}
				) '1' else '0'
				val filteredData = data.values.flatten().filter { it[bit] == mostCommonChar }
				if (filteredData.size == 1)
					return filteredData.first()
				bit++
				data = filteredData.groupBy { it[bit] }
			}
			throw IllegalArgumentException("Input data is insufficient")
		}
		return getNumber('1').toInt(2) * getNumber('0').toInt(2)
	}

	val input = readInput("Day03")
	println(part1(input))
	println(part2(input))
}
