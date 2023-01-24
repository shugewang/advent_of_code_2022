// day 8
import java.io.File

class Treehouse (private val fileName: String) {
    lateinit var treeMap: List<List<Int>>
    fun convertMapTo2DList(): List<List<Int>> {
        val input = File(fileName).readLines().map { it.split("").drop(1).dropLast(1) }
        treeMap = input.map { it.map { it.toInt()} }
        return treeMap
    }

    fun countEdgeTrees(): Int {
        val totalTrees = treeMap.size*treeMap[0].size
        val edgeTrees = treeMap.size*2 + treeMap[0].size*2 - 4
        val innerTrees = totalTrees - edgeTrees
        return edgeTrees
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

    fun checkIfVisible(rowIndex: Int, colIndex: Int): Boolean {
        return checkIfVisibleByCol(rowIndex, colIndex) || checkIfVisibleByRow(rowIndex, colIndex)
    }

    fun countNumberOfVisibleInnerTrees(): Int {
        var visibleInnerTrees = 0
        for (row in 1..treeMap.size-2) {
            for (col in 1.. treeMap[row].size-2) {
                if (checkIfVisible(row, col)) {
                    visibleInnerTrees++
                }
            }
        }
        return visibleInnerTrees
    }

    fun countNumberOfAllVisibleTrees(edgeTrees: Int, visibleInnerTrees: Int): Int {
        return edgeTrees + visibleInnerTrees
    }

    private fun checkIfEdge(rowIndex: Int, colIndex: Int): Boolean {
        if (rowIndex == 0 || colIndex == 0 || rowIndex == treeMap.size - 1 || colIndex == treeMap[rowIndex].size - 1) {
            return true
        }
        return false
    }
    fun getScenicScore(rowIndex: Int, colIndex: Int): Int? {
        return if (checkIfEdge(rowIndex, colIndex)) 0 else null
    }


}
