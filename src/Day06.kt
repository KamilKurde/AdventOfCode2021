import java.util.*

class LanternFish(val creatingInterval: Int, var remainingDays: Int = creatingInterval) {
	fun nextDay(list: MutableList<LanternFish>) {
		remainingDays--
		if (remainingDays < 0) {
			remainingDays = creatingInterval
			list += LanternFish(creatingInterval, creatingInterval + 2)
		}
	}
}

fun main() {
	fun howManyLanternFishes(days: Int, initialState: MutableList<LanternFish>): Int {
		val lanternFishes = if(days > 100) LinkedList<LanternFish>() else mutableListOf()
		lanternFishes += initialState
		repeat(days)
		{
			for (i in lanternFishes.indices)
				lanternFishes[i].nextDay(lanternFishes)
			println("$it: ${lanternFishes.size}")
			//println("After ${it + 1} days: ${lanternFishes.map { it.remainingDays }.joinToString(",")}")
		}
		return lanternFishes.size
	}

	fun part1(input: List<String>) = howManyLanternFishes(80, input.joinToString(",").split(",").map { LanternFish(6, it.toInt()) }.toMutableList())

	fun part2(input: List<String>) = howManyLanternFishes(256, input.joinToString(",").split(",").map { LanternFish(6, it.toInt()) }.toMutableList())

	val input = readInput("Day06")
	println(part1(input))
	println(part2(input))
}