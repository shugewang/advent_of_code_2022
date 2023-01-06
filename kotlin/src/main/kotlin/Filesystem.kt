import java.io.File

class Filesystem (private val fileName: String) {
    fun getChangeDirLines(): List<String> {
        return File(fileName).readLines().filter { it.contains("cd") }.filterNot { it.contains("..") }
    }
}
