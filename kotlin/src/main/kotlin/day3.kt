fun splitIntoCompartments(rucksacks: List<String>): List<List<String>> {
    return rucksacks.map { it.chunked(it.length / 2) }
}

fun findCommonChar(rucksack: List<String>): Char? {
    for (i in rucksack[0]) {
        for (j in rucksack[1]) {
            if (i == j) return i
        }
    }
    return null
}

fun getPriority(commonChar: Char?): Int {
    val itemTypes = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
    val priority = itemTypes.indexOf(commonChar!!)
    return priority+1
}

fun getSumPriorities(splitRucksacks: List<List<String>>): Int{
    val priorities = splitRucksacks.map { getPriority(findCommonChar(it)) }
    return priorities.sum()
}

fun getGroups(rucksacks: List<String>): List<List<String>> {
    return rucksacks.chunked(3)
}

fun findCommonCharForGroup(group: List<String>): Char? {
    for (i in group[0]) {
        for (j in group[1]) {
            for (k in group[2]) {
                if (i == j && j == k) return i
            }
        }
    }
    return null
}

fun getSumPrioritiesForGroups(groups: List<List<String>>): Int {
    val priorities = groups.map { getPriority(findCommonCharForGroup(it)) }
    return priorities.sum()
}

fun main() {
    val rucksacks = readFile("rucksack.txt")
    val splitRucksacks = splitIntoCompartments(rucksacks)
    val sumPriorities = getSumPriorities(splitRucksacks)
    println("part 1 solution: $sumPriorities")

    val groups = getGroups(rucksacks)
    val sumPrioritiesForGroups = getSumPrioritiesForGroups(groups)
    println("part 2 solution: $sumPrioritiesForGroups")
}
