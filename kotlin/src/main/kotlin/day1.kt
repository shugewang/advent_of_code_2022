import java.io.File

fun readFile(fileName: String): List<String>
        = File(fileName).absoluteFile.readLines()


fun <T> List<T>.split(predicate: (T) -> Boolean): List<List<T>> =
    fold(mutableListOf(mutableListOf<T>())) { acc, t ->
        if (predicate(t)) acc.add(mutableListOf())
        else acc.last().add(t)
        acc
    }.filterNot { it.isEmpty() }

fun getTotalCalories(caloriesRecordByElf: List<List<String>>): List<Int> {
    val caloriesRecordByElfInt: List<List<Int>> = caloriesRecordByElf.map { it -> it.map { it.toInt() } }
    return caloriesRecordByElfInt.map { it.sum() }
}

fun getMostTotalCalories(totalCaloriesByElf: List<Int>): Int {
    return totalCaloriesByElf.sortedDescending()[0]
}

fun getTopThreeMostTotalCalories(totalCaloriesByElf: List<Int>): Int {
    return totalCaloriesByElf.sortedDescending().take(3).sum()
}

fun main() {
    val caloriesRecord: List<String> = readFile("calories.txt")
    val caloriesRecordByElf: List<List<String>> = caloriesRecord.split { it.isBlank() }
    val totalCaloriesByElf = getTotalCalories(caloriesRecordByElf)
    val mostCalories = getMostTotalCalories(totalCaloriesByElf)
    println("most calories: $mostCalories")

    val mostCaloriesTopThree = getTopThreeMostTotalCalories(totalCaloriesByElf)
    println("most calories by top three elves: $mostCaloriesTopThree")
}
