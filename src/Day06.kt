data class LanternFish(val creatingInterval: Int, private val list: MutableList<LanternFish>, var remainingDays: Int = creatingInterval) {
	fun nextDay() {
		remainingDays--
		if (remainingDays < 0) {
			remainingDays = creatingInterval
			list += LanternFish(creatingInterval, list, creatingInterval + 2)
		}
	}
}

fun main() {
	fun part1(input: List<String>): Int {
		val lanternFishes = mutableListOf<LanternFish>()
		lanternFishes += input.joinToString(",").split(",").map { LanternFish(6, lanternFishes, it.toInt()) }
		repeat(80)
		{
			for (i in lanternFishes.indices)
				lanternFishes[i].nextDay()
		}
		return lanternFishes.size
	}

	fun part2(input: List<String>) {
		TODO()
	}

	val input = readInput("Day06")
	println(part1(input))
	println(part2(input))
}