import java.io.File

class Treehouse (private val fileName: String) {
    lateinit var treeMap: List<List<Int>>
    fun convertMapTo2DList(): List<List<Int>> {
        val input = File(fileName).readLines().map { it.split("").drop(1).dropLast(1) }
        treeMap = input.map { it.map { it.toInt()} }
        return treeMap
    }

    fun countInnerTrees(): Int {
        val totalTrees = treeMap.size*treeMap[0].size
        val edgeTrees = treeMap.size*2 + treeMap[0].size*2 - 4
        val innerTrees = totalTrees - edgeTrees
        return innerTrees
    }

    fun checkIfVisibleByRow(rowIndex: Int, colIndex: Int): Boolean {
        val tree = treeMap[rowIndex][colIndex]
        val leftTrees = treeMap[rowIndex].slice(0 until colIndex)
        val rightTrees = treeMap[rowIndex].slice(colIndex + 1 until treeMap[colIndex].size)
        return tree > leftTrees.max() || tree > rightTrees.max()
    }

    fun checkIfVisibleByCol(rowIndex: Int, colIndex: Int): Boolean {
        val tree = treeMap[rowIndex][colIndex]
        val aboveTrees = mutableListOf<Int>()

        treeMap.take(rowIndex).map { aboveTrees.add(it[colIndex])}

        val belowTrees = mutableListOf<Int>()
        treeMap.slice(rowIndex + 1 until treeMap.size).map { belowTrees.add(it[colIndex])}
        return tree > aboveTrees.max() || tree > belowTrees.max()
    }

}
