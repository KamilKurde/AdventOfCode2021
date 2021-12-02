fun List<String>.toIntList() = this.filterNot { it.isEmpty() }.map { it.toInt() }

fun main() {
	fun part1(input: List<String>): Int {
		val numbers = input.toIntList()
		var counter = 0
		for (i in 1 until numbers.size)
			if (numbers[i] > numbers[i - 1])
				counter++
		return counter
	}

	fun part2(input: List<String>): Int {
		val numbers = input.toIntList()
		val windowed = numbers.windowed(3, partialWindows = true)
		return part1(windowed.map { it.sum().toString() })
	}

	val input = readInput("Day01")
	println(part1(input))
	println(part2(input))
}
