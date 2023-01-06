import java.io.File

class Filesystem (private val fileName: String) {
    fun extractDirectories(): List<String> {
        var input =  File(fileName).readLines().filter { it.contains("cd") }.filterNot { it.contains("..") }
        return input.map { it.split(" ").takeLast(1).joinToString()}
    }


}

class Item (var type: String, var size: Int) {
}

