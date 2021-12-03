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
		TODO()
	}

	val input = readInput("Day03")
	println(part1(input))
	println(part2(input))
}
