class Problem3 {
    companion object {
        fun part1() {
            val schematic =
                readInput("/Users/jxd040/dev/advent-of-code-2023/src/main/resources/problem-3/input.txt")

            val validNumbers = mutableListOf<Int>()

            schematic.forEachIndexed { index, line ->
                var numberStart = 0
                while (numberStart < line.length) {
                    if (line[numberStart].isDigit()) {
                        var numberEnd = numberStart
                        while (numberEnd + 1 < line.length && line[numberEnd + 1].isDigit()) {
                            numberEnd++
                        }
                        val isValidNumber = when (index) {
                            0 -> {
                                containsAdjacentSymbols(
                                    previousLine = ".".repeat(line.length),
                                    currentLine = line,
                                    nextLine = schematic[1],
                                    numberStart = numberStart,
                                    numberEnd = numberEnd
                                )
                            }

                            schematic.size - 1 -> {
                                containsAdjacentSymbols(
                                    previousLine = schematic[index - 1],
                                    currentLine = line,
                                    nextLine = ".".repeat(line.length),
                                    numberStart = numberStart,
                                    numberEnd = numberEnd
                                )
                            }

                            else -> {
                                containsAdjacentSymbols(
                                    previousLine = schematic[index - 1],
                                    currentLine = line,
                                    nextLine = schematic[index + 1],
                                    numberStart = numberStart,
                                    numberEnd = numberEnd
                                )
                            }
                        }
                        if (isValidNumber) {
                            validNumbers.add(line.substring(numberStart, numberEnd + 1).toInt())
                        }
                        numberStart = numberEnd + 1
                    } else {
                        numberStart++
                    }
                }
            }
            println(validNumbers)
            println(validNumbers.sum())
        }

        fun containsAdjacentSymbols(
            previousLine: String,
            currentLine: String,
            nextLine: String,
            numberStart: Int,
            numberEnd: Int,
        ): Boolean {
            var trimmedNumberStart = numberStart
            if (trimmedNumberStart == 0) {
                trimmedNumberStart += 1
            }

            var trimmedNumberEnd = numberEnd
            if (trimmedNumberEnd == currentLine.length - 1) {
                trimmedNumberEnd -= 1
            }

            val adjacentChars = listOf(
                previousLine.substring(trimmedNumberStart - 1, trimmedNumberEnd + 2).map { it },
                listOf(currentLine[trimmedNumberStart - 1]),
                listOf(currentLine[trimmedNumberEnd + 1]),
                nextLine.substring(trimmedNumberStart - 1, trimmedNumberEnd + 2).map { it },
            ).flatten()

            return adjacentChars.any { isSymbol(it) }
        }

        fun isSymbol(char: Char) =
            char != '.' && !char.isDigit()
    }
}
