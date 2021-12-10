fun main() {
	fun howManyLanternFishes(days: Int, initialList: List<String>): ULong {
		var lanternFishesIndex = Array(9) { 0UL }
		initialList.map { it.toInt() }.forEach {
			lanternFishesIndex[it]++
		}
		repeat(days)
		{
			val after = Array(9) { 0UL }
			for (i in lanternFishesIndex.indices) {
				if (i == 0) {
					after[8] = lanternFishesIndex[i]
					after[6] = lanternFishesIndex[i]
				} else {
					after[i - 1] += lanternFishesIndex[i]
				}
			}
			lanternFishesIndex = after
		}
		return lanternFishesIndex.sum()
	}

	fun part1(input: List<String>) = howManyLanternFishes(80, input.joinToString(",").split(","))

	fun part2(input: List<String>) = howManyLanternFishes(256, input.joinToString(",").split(","))

	val input = readInput("Day06")
	println(part1(input))
	println(part2(input))
}