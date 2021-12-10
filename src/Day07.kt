import kotlin.math.abs

fun main() {
	fun part1(input: List<String>): Int {
		val positions = input.joinToString(",").split(",").map { it.toInt() }
		val possiblePositionCosts = Array(positions.maxOf { it }) { possiblePosition ->
			positions.sumOf { abs(it - possiblePosition) }
		}
		return possiblePositionCosts.minOf { it }
	}

	fun part2(input: List<String>): Int {
		val positions = input.joinToString(",").split(",").map { it.toInt() }
		val possiblePositionCosts = Array(positions.maxOf { it }) { possiblePosition ->
			positions.sumOf {
				var accumulator = 0
				for (i in 1..abs(it - possiblePosition)) {
					accumulator += i
				}
				accumulator
			}
		}
		return possiblePositionCosts.minOf { it }
	}

	val input = readInput("Day07")
	println(part1(input))
	println(part2(input))
}