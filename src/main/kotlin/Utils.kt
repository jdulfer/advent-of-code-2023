import java.io.BufferedReader
import java.io.FileReader

fun readInput(filename: String): List<String> {
    val result = mutableListOf<String>()

    val filePath = filename
    var reader: BufferedReader? = null

    try {
        reader = BufferedReader(FileReader(filePath))
        var line: String?

        while (reader.readLine().also { line = it } != null) {
            result.add(line!!)
        }
    } catch (e: Exception) {
        println("An error occurred: ${e.message}")
    } finally {
        try {
            reader?.close()
        } catch (e: Exception) {
            println("An error occurred while closing the file: ${e.message}")
        }
    }

    return result
}
