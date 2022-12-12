fun readFileDay2(fileName: String): List<List<String>> {
    var input: List<String> = readFile(fileName)
    input = input.map {
        it.replace("A", "rock").replace("B","paper").replace("C","scissors").
        replace("X", "lose").replace("Y","draw").replace("Z","win")}
    var strategyGuide: List<List<String>> = input.map { it.split(" ")}
    return strategyGuide
}

fun getScoreForEachRoundCorrect(roundStrategy: List<String>, totalScore: Int): Int {
    var score = totalScore
    var opponentChoice: String = roundStrategy[0]
    var strategy: String = roundStrategy[1]
    var yourChoice: String = ""
    score += when (strategy) {
        "lose" -> 0
        "draw" -> 3
        else -> 6
    }
    if (opponentChoice == "rock") {
        score += when (strategy) {
            "lose" -> 3
            "draw" -> 1
            else -> 2
        }
    } else if (opponentChoice == "paper") {
        score += when (strategy) {
            "lose" -> 1
            "draw" -> 2
            else -> 3
        }
    } else if (opponentChoice == "scissors") {
        score += when (strategy) {
            "lose" -> 2
            "draw" -> 3
            else -> 1
        }
    }
    return score
}

fun getScoreForEachRound(roundStrategy: List<String>, totalScore: Int): Int {
    var score = totalScore
    var opponentChoice: String = roundStrategy[0]
    var yourChoice: String = roundStrategy[1]
    score += when (yourChoice) {
        "rock" -> 1
        "paper" -> 2
        else -> 3
    }
    if (opponentChoice == yourChoice) score += 3
    else if (opponentChoice == "rock" && yourChoice == "paper") score += 6
    else if (opponentChoice == "paper" && yourChoice == "scissors") score += 6
    else if (opponentChoice == "scissors" && yourChoice == "rock") score += 6
    return score
}

fun getTotalScore(strategyGuide: List<List<String>>): Int {
    var score: Int = 0
    for (round in strategyGuide) {
        score = getScoreForEachRoundCorrect(round, score)
    }
    return score
}



fun main() {
    val strategyGuide = readFileDay2("RPS_Strategy_Guide.txt")
    println(strategyGuide)
    var totalScore = getTotalScore(strategyGuide)
    println(totalScore)
}