class Problem1 {
    companion object {
        fun part1() {
            val inputs = readInput("/Users/jxd040/dev/advent-of-code-2023/src/main/resources/problem-1/input.txt")
            val digits = inputs.map { input ->
                val digitList = mutableListOf<Int>()
                input.forEach { c ->
                    if (c.isDigit()) {
                        digitList.add(c.digitToInt())
                    }
                }
                return@map digitList
            }

            val result = digits.map { digitList ->
                (digitList.first().toString() + digitList.last().toString()).toInt()
            }

            println("Part 1: ${result.sum()}")
        }

        fun part2() {
            val inputs = readInput("/Users/jxd040/dev/advent-of-code-2023/src/main/resources/problem-1/input.txt")
            val convertedDigits = inputs.map { convertStringNumbers(it) }
            val digits = convertedDigits.map { input ->
                val digitList = mutableListOf<Int>()
                input.forEach { c ->
                    if (c.isDigit()) {
                        digitList.add(c.digitToInt())
                    }
                }
                return@map digitList
            }

            val result = digits.map { digitList ->
                if (digitList.first() == 0) {
                    return@map digitList.last()
                }
                (digitList.first().toString() + digitList.last().toString()).toInt()
            }

            println(convertedDigits)
            println(digits)
            println(result)
            println("Part 2: ${result.sum()}")
        }

        fun convertStringNumbers(string: String): String {
            var resultString = string + "zzzzzz"
            var i = 5
            while (i <= resultString.length) {
                var subString = resultString.substring(i - 5, i)
                Numbers.entries.forEach { number ->
                    val numberString = number.toString()
                    subString =
                        subString.replace(
                            numberString,
                            numberString.first() + number.num.toString() + numberString.last()
                        )
                }
                resultString = resultString.replaceRange(i - 5, i, subString)
                i++
            }
            return resultString
        }

        enum class Numbers(val num: Int) {
            one(1),
            two(2),
            three(3),
            four(4),
            five(5),
            six(6),
            seven(7),
            eight(8),
            nine(9),
        }
    }
}
