import java.io.File

fun readFile(fileName: String): List<String>
        = File(fileName).absoluteFile.readLines()


fun <T> List<T>.split(predicate: (T) -> Boolean): List<List<T>> =
    fold(mutableListOf(mutableListOf<T>())) { acc, t ->
        if (predicate(t)) acc.add(mutableListOf())
        else acc.last().add(t)
        acc
    }.filterNot { it.isEmpty() }

//fun getTotalCalories(caloriesRecordByElf: List<List<String>>) {
//    val totalCaloriesByElf = caloriesRecordByElf.map { it.sum() }
//}

fun main() {
    val caloriesRecord: List<String> = readFile("calories_test.txt")
    val caloriesRecordByElf: List<List<String>> = caloriesRecord.split { it.isBlank() }
    println(caloriesRecord)
    println(caloriesRecordByElf)
}
