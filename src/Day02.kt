fun main() {
	fun part1(input: List<String>): Int {
		var horizontal = 0
		var depth = 0
		input.filterNot { it.isEmpty() }.forEach {
			val split = it.split(" ")
			val command = split[0]
			val value = split[1].toInt()
			when (command) {
				"forward" -> horizontal += value
				"down" -> depth += value
				"up" -> depth -= value
			}
		}
		return horizontal * depth
	}

	fun part2(input: List<String>): Int {
		var horizontal = 0
		var depth = 0
		var aim = 0
		input.filterNot { it.isEmpty() }.forEach {
			val split = it.split(" ")
			val command = split[0]
			val value = split[1].toInt()
			when (command) {
				"forward" -> {
					horizontal += value
					depth += aim * value
				}
				"down" -> {
					aim += value
				}
				"up" -> {
					aim -= value
				}
			}
		}
		return horizontal * depth
	}

	val input = readInput("Day02")
	println(part1(input))
	println(part2(input))
}
