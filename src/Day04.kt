data class Board(val nodes: Array<Array<Node>>) {
	data class Node(val number: Int, var checked: Boolean = false) {
		override fun toString() = number.toString()
	}

	constructor(numbers: List<List<Int>>) : this(Array(numbers.size) { row -> Array(numbers[row].size) { column -> Node(numbers[row][column]) } })

	override fun toString() = nodes.mapIndexed { index, row ->
		"$index: " + row.joinToString()
	}.joinToString("\n")

	fun check(number: Int) {
		nodes.forEach { row ->
			row.forEach {
				if (it.number == number)
					it.checked = true
			}
		}
	}

	val won
		get() =
			// All in given row
			nodes.any { row -> row.all { it.checked } } ||
					// All in given column
					nodes.first().indices.any { column ->
						var all = true
						for (row in nodes.indices) {
							if (!nodes[row][column].checked)
								all = false
						}
						all
					}
}

fun main() {
	fun generateNumbers(numbers: String) = numbers.split(",").map { it.toInt() }.iterator()
	fun generateBoards(data: List<String>) = data.filterNot { it.isEmpty() }
		.windowed(5, 5)
		.map { board ->
			Board(board.map { row ->
				row.split(" ")
					.filterNot { it.isEmpty() }
					.map { it.toInt() }
			})
		}

	fun part1(input: List<String>): Int {
		val numbers = generateNumbers(input.first())
		val boards = generateBoards(input.drop(1))
		var number = 0
		while (boards.none { it.won }) {
			number = numbers.next()
			boards.forEach { it.check(number) }
		}
		return boards.first { it.won }.nodes.flatten().filterNot { it.checked }.sumOf { it.number } * number
	}

	fun part2(input: List<String>): Int {
		val numbers = generateNumbers(input.first())
		val boards = generateBoards(input.drop(1))
		var number = 0
		val boardsThatWon = mutableListOf<Board>()
		while (!boards.all { it.won }) {
			number = numbers.next()
			boards.forEach {
				it.check(number)
				if (it.won && it !in boardsThatWon)
					boardsThatWon += it
			}
		}
		return boardsThatWon.last().nodes.flatten().filterNot { it.checked }.sumOf { it.number } * number
	}

	val input = readInput("Day04")
	println(part1(input))
	println(part2(input))
}