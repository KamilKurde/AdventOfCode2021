data class Line(val x1: Int, val x2: Int, val y1: Int, val y2: Int) {
	data class Point(val x: Int, val y: Int)

	val points
		get(): List<Point> {
			val xIndices = when {
				x1 < x2 -> x1..x2
				else -> x2..x1
			}
			val yIndices = when {
				y1 < y2 -> y1..y2
				else -> y2..y1
			}
			return (xIndices.map { Point(it, y1) } + yIndices.map { Point(x1, it) }).distinct()
		}
}

fun main() {
	fun part1(input: List<String>): Int {
		val points = mutableMapOf<Line.Point, Int>()
		val lines = input.map {
			val split = it.replace(" -> ", ",").split(",").map { number -> number.toInt() }
			Line(split[0], split[2], split[1], split[3])
		}.filter {
			it.x1 == it.x2 || it.y1 == it.y2
		}
		lines.forEach { line ->
			line.points.forEach { it ->
				points[it] = (points[it] ?: 0) + 1
			}
		}
		return points.values.count { it >= 2 }
	}

	fun part2(input: List<String>): Int {
		TODO()
	}

	val input = readInput("Day05")
	println(part1(input))
	println(part2(input))
}