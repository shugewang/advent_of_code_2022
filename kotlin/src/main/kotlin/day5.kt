fun refineCargos(cargos: List<String>): ArrayList<ArrayList<String>>{
    val numberOfStacks: Int = cargos.last().max().digitToInt()
    val stacks = cargos.dropLast(1)
    val refinedStacks: ArrayList<ArrayList<String>> = arrayListOf(arrayListOf())
    for (i in 1..numberOfStacks) {
        val startIndex = (i-1)*4
        if (i != 1) refinedStacks.add(arrayListOf())
        for (stack in stacks) {
            refinedStacks[i-1].add(stack.substring(startIndex,startIndex+3))
        }
    }
    refinedStacks.map {  it.removeAll(listOf("   "))}
    return refinedStacks
}

fun refineInstructions(instructions: List<String>): List<List<String>> {
    return instructions.map { it.split(" ") }
}

fun moveCargoes(refinedCargos: ArrayList<ArrayList<String>>, refinedInstructions: List<List<String>>) {
    for (instruction in refinedInstructions) {
        val itemsToMove = instruction[1].toInt()
        val moveFrom = instruction[3].toInt()-1
        val moveTo = instruction[5].toInt()-1
//        for (i in 1..itemsToMove) {
//            refinedCargos[moveTo].add(0, refinedCargos[moveFrom].take(1)[0])
//            refinedCargos[moveFrom].removeAt(0)
//        }

        for (i in 1..itemsToMove) {
            refinedCargos[moveTo].add(i-1, refinedCargos[moveFrom].take(1)[0])
            refinedCargos[moveFrom].removeAt(0)
        }
    }

    for (stack in refinedCargos) {
        println(stack)
    }
}

fun main() {
    val cargos = readFile("cargo_rearrangement.txt").specialSplit { it.isBlank() }[0]
    val instructions = readFile("cargo_rearrangement.txt").specialSplit { it.isBlank() }[1]
    val refinedCargos = refineCargos(cargos)
    val refinedInstructions = refineInstructions(instructions)

    moveCargoes(refinedCargos,refinedInstructions)
}
