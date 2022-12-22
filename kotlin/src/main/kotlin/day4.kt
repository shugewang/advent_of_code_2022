fun checkCompleteOverlap(sectionAssignmentPair: List<String>): Boolean{
    val pair1 = sectionAssignmentPair[0].split(",")[0].split("-").map { it.toInt()}
    val pair2 = sectionAssignmentPair[0].split(",")[1].split("-").map { it.toInt()}
    if (pair2[0] >= pair1[0] && pair2[1] <= pair1[1] ||
        pair1[0] >= pair2[0] && pair1[1] <= pair2[1]) {
        return true
    }
    return false
}

fun checkPartialOverlap(sectionAssignmentPair: List<String>): Boolean {
    val pair1 = sectionAssignmentPair[0].split(",")[0].split("-").map { it.toInt()}
    val pair2 = sectionAssignmentPair[0].split(",")[1].split("-").map { it.toInt()}
    if (pair2[0] <= pair1[0] && pair1[0] <= pair2[0] || pair1[0] <= pair2[0] && pair2[0] <= pair1[0] ||
        pair2[1] <= pair1[1] && pair1[1] <= pair2[1] || pair1[1] <= pair2[1] && pair2[1] <= pair1[1]) {
        return true
    }
    for (i in pair1[0]..pair1[1]) {
        if (i >= pair2[0] && i <= pair2[1]) {
            return true
        }
    }
    for (j in pair2[0]..pair2[1]) {
        if (j >= pair1[0] && j <= pair1[1]) {
            return true
        }
    }
    return false
}

fun checkNumberOfCompleteOverlaps(sectionAssignments: List<List<String>>): Int {
    var count = 0
    sectionAssignments.map { if(checkCompleteOverlap(it)) count++}
    return count
}

fun checkNumberOfPartialOverlaps(sectionAssignments: List<List<String>>): Int {
    var count = 0
    sectionAssignments.map { if(checkPartialOverlap(it)) count++}
    return count
}

fun main() {
    val sectionAssignments: List<List<String>> = readFile("section_assignments.txt").chunked(1)
    val count = checkNumberOfCompleteOverlaps(sectionAssignments)
    val completeAndPartialCount = checkNumberOfPartialOverlaps(sectionAssignments)
    println("part 1 solution: $count")
    println("part 2 solution: $completeAndPartialCount")

}