class Problem2 {
    companion object {
        fun part1() {
            val input = readInput("/Users/jxd040/dev/advent-of-code-2023/src/main/resources/problem-2/input.txt")
            val games = input.groupBy(
                { game -> game.split(":")[0].substring(5) },
                { game ->
                    game.split(":")[1].split(";")
                        .map { reveal -> reveal.split(",").map { it.trim() } }
                }
            )

            val flatGames = games.mapValues { it.value.flatten() }

            val validGames = flatGames.filter { game ->
                val reveals = game.value
                reveals.forEach { reveal ->
                    if (!isValidDraw(reveal)) {
                        return@filter false
                    }
                }
                true
            }
            println(validGames.keys.map { it.toInt() }.sum())
        }

        fun part2() {
            val input = readInput("/Users/jxd040/dev/advent-of-code-2023/src/main/resources/problem-2/input.txt")
            val games = input.groupBy(
                { game -> game.split(":")[0].substring(5) },
                { game ->
                    game.split(":")[1].split(";")
                        .map { reveal -> reveal.split(",").map { it.trim() } }
                }
            )

            val flatGames = games.mapValues { it.value.flatten() }

            val requiredMaximums = flatGames.mapValues { game ->
                getGameMaximums(game.value)
            }

            val powers = requiredMaximums.map { it.value.red * it.value.green * it.value.blue }
            val result = powers.sum()
            println(requiredMaximums)
            println(powers)
            println(result)
        }

        private fun getGameMaximums(reveals: List<List<String>>): GameMaximums {
            val gameMaximums = GameMaximums(0, 0, 0)

            reveals.forEach { reveal ->
                reveal.forEach { cube ->
                    val number = cube.split(" ")[0].toInt()
                    val colour = cube.split(" ")[1]
                    when (colour) {
                        "red" -> if (number > gameMaximums.red) {
                            gameMaximums.red = number
                        }

                        "green" -> if (number > gameMaximums.green) {
                            gameMaximums.green = number
                        }

                        "blue" -> if (number > gameMaximums.blue) {
                            gameMaximums.blue = number
                        }
                    }
                }
            }
            return gameMaximums
        }

        private fun isValidDraw(reveal: List<String>): Boolean {
            val gameMaximums = GameMaximums(0, 0, 0)
            reveal.forEach { cube ->
                val number = cube.split(" ")[0].toInt()
                val colour = cube.split(" ")[1]
                when (colour) {
                    "red" -> gameMaximums.red = number
                    "green" -> gameMaximums.green = number
                    "blue" -> gameMaximums.blue = number
                }
            }
            return (gameMaximums.red <= Maximums.Red.maxNumber &&
                gameMaximums.green <= Maximums.Green.maxNumber &&
                gameMaximums.blue <= Maximums.Blue.maxNumber)
        }
    }

}

enum class Maximums(val maxNumber: Int) {
    Red(12),
    Green(13),
    Blue(14)
}

data class GameMaximums(var red: Int, var green: Int, var blue: Int)
