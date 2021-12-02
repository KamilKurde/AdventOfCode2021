fun main() {
	fun part1(input: List<String>): Int {
		val numbers = input.filterNot { it.isEmpty() }.map { it.toInt() }
		var counter = 0
		for (i in 1 until numbers.size)
			if (numbers[i] > numbers[i - 1])
				counter++
		return counter
	}

	fun part2(input: List<String>) = part1(
		input.windowed(3, partialWindows = true)
		{ window -> window.sumOf { element -> element.toInt() }.toString() }
	)

	val input = readInput("Day01")
	println(part1(input))
	println(part2(input))
}