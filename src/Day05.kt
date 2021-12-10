data class Line(val x1: Int, val x2: Int, val y1: Int, val y2: Int, val includeDiagonals: Boolean = false) {
	data class Point(val x: Int, val y: Int)

	val points
		get(): List<Point> {
			return when {
				y1 == y2 -> when (x1 < x2) {
					true -> x1..x2
					false -> x2..x1
				}.map { Point(it, y1) }
				x1 == x2 -> when (y1 < y2) {
					true -> y1..y2
					false -> y2..y1
				}.map { Point(x1, it) }
				else -> {
					val xRange = if (x1 > x2) x1 downTo x2 else x1..x2
					val yRange = if (y1 > y2) y1 downTo y2 else y1..y2
					val yIterator = yRange.iterator()
					if (includeDiagonals) xRange.map {
						Point(it, yIterator.next())
					}
					else emptyList()
				}
			}
		}
}

fun main() {
	fun part1(input: List<String>): Int {
		val points = mutableMapOf<Line.Point, Int>()
		val lines = input.map {
			val split = it.replace(" -> ", ",").split(",").map { number -> number.toInt() }
			Line(split[0], split[2], split[1], split[3])
		}
		lines.forEach { line ->
			line.points.forEach { it ->
				points[it] = (points[it] ?: 0) + 1
			}
		}
		return points.values.count { it >= 2 }
	}

	fun part2(input: List<String>): Int {
		val points = mutableMapOf<Line.Point, Int>()
		val lines = input.map {
			val split = it.replace(" -> ", ",").split(",").map { number -> number.toInt() }
			Line(split[0], split[2], split[1], split[3], true)
		}
		lines.forEach { line ->
			line.points.forEach { it ->
				points[it] = (points[it] ?: 0) + 1
			}
		}
		return points.values.count { it >= 2 }
	}

	val input = readInput("Day05")
	println(part1(input))
	println(part2(input))
}