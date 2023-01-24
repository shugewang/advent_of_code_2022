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
        if (checkIfEdge(rowIndex, colIndex)) {
            return 0
        } else {
            return getUnblockedViewToLeft(rowIndex, colIndex)*getUnblockedViewToRight(rowIndex, colIndex)*getUnblockedViewUp(rowIndex, colIndex)*getUnblockedViewDown(rowIndex, colIndex)
        }
    }

    private fun getNumberOfVisibleTrees(trees: List<Int>, rowIndex: Int, colIndex: Int): Int{
        var unblocked = 0
        for (tree in trees) {
            if (tree == treeMap[rowIndex][colIndex]) {
                unblocked++
                return unblocked
            } else if (tree < treeMap[rowIndex][colIndex]) {
                unblocked++
            }
        }
        return unblocked
    }
    fun getUnblockedViewToLeft(rowIndex: Int, colIndex: Int): Int {
        var visibleTreesCount = 0
        if (!checkIfEdge(rowIndex, colIndex)) {
            val leftTreesReversed = treeMap[rowIndex].slice(0 until colIndex).reversed()
            visibleTreesCount = getNumberOfVisibleTrees(leftTreesReversed, rowIndex, colIndex)
        }
        return visibleTreesCount
    }

    fun getUnblockedViewToRight(rowIndex: Int, colIndex: Int): Int {
        var visibleTreesCount = 0
        if (!checkIfEdge(rowIndex, colIndex)) {
            val rightTrees = treeMap[rowIndex].slice(colIndex + 1 until treeMap[colIndex].size)
            visibleTreesCount = getNumberOfVisibleTrees(rightTrees, rowIndex, colIndex)
        }
        return visibleTreesCount
    }

    fun getUnblockedViewUp(rowIndex: Int, colIndex: Int): Int {
        var visibleTreesCount = 0
        if (!checkIfEdge(rowIndex, colIndex)) {
            val aboveTrees = mutableListOf<Int>()
            treeMap.take(rowIndex).map { aboveTrees.add(it[colIndex])}
            visibleTreesCount = getNumberOfVisibleTrees(aboveTrees.reversed(), rowIndex, colIndex)
        }
        return visibleTreesCount
    }

    fun getUnblockedViewDown(rowIndex: Int, colIndex: Int): Int {
        var visibleTreesCount = 0
        if (!checkIfEdge(rowIndex, colIndex)) {
            val belowTrees = mutableListOf<Int>()
            treeMap.slice(rowIndex + 1 until treeMap.size).map { belowTrees.add(it[colIndex])}
            visibleTreesCount = getNumberOfVisibleTrees(belowTrees, rowIndex, colIndex)
        }
        return visibleTreesCount
    }


}
