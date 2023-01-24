import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TreehouseTest {
    var treehouse = Treehouse("tree_height_map.txt")

    @Test
    fun convertInputTo2DListTest() {
        treehouse.treeMap = listOf(listOf(3,0,3,7,3), listOf(2,5,5,1,2), listOf(6,5,3,3,2), listOf(3,3,5,4,9), listOf(3,5,3,9,0))
        val expected = listOf(listOf(3,0,3,7,3), listOf(2,5,5,1,2), listOf(6,5,3,3,2), listOf(3,3,5,4,9), listOf(3,5,3,9,0))
        assertEquals(expected, treehouse.convertMapTo2DList())
    }

    @Test
    fun countNumberOfEdgeTrees() {
        treehouse.treeMap = listOf(listOf(3,0,3,7,3), listOf(2,5,5,1,2), listOf(6,5,3,3,2), listOf(3,3,5,4,9), listOf(3,5,3,9,0))
        assertEquals(16, treehouse.countEdgeTrees())
    }

    @Test
    fun checkIfVisibleRowTrueTest() {
        treehouse.treeMap = listOf(listOf(3,0,3,7,3), listOf(2,5,5,1,2), listOf(6,5,3,3,2), listOf(3,3,5,4,9), listOf(3,5,3,9,0))
        assertEquals(true, treehouse.checkIfVisibleByRow(2, 1))
    }

    @Test
    fun checkIfVisibleRowFalseTest() {
        treehouse.treeMap = listOf(listOf(3,0,3,7,3), listOf(2,5,5,1,2), listOf(6,5,3,3,2), listOf(3,3,5,4,9), listOf(3,5,3,9,0))
        assertEquals(false, treehouse.checkIfVisibleByRow(2, 2))
    }

    @Test
    fun checkIfVisibleColTrueTest() {
        treehouse.treeMap = listOf(listOf(3,0,3,7,3), listOf(2,5,5,1,2), listOf(6,5,3,3,2), listOf(3,3,5,4,9), listOf(3,5,3,9,0))
        assertEquals(true, treehouse.checkIfVisibleByCol(1, 1))
    }

    @Test
    fun checkIfVisibleColFalseTest() {
        treehouse.treeMap = listOf(listOf(3,0,3,7,3), listOf(2,5,5,1,2), listOf(6,5,3,3,2), listOf(3,3,5,4,9), listOf(3,5,3,9,0))
        assertEquals(false, treehouse.checkIfVisibleByCol(3, 1))
    }

    @Test
    fun checkIfVisibleRowAndColTest() {
        treehouse.treeMap = listOf(listOf(3,0,3,7,3), listOf(2,5,5,1,2), listOf(6,5,3,3,2), listOf(3,3,5,4,9), listOf(3,5,3,9,0))
        assertEquals(true, treehouse.checkIfVisible(1,2))
    }

    @Test
    fun countNumberOfVisibleInnerTreesTest() {
        treehouse.treeMap = listOf(listOf(3,0,3,7,3), listOf(2,5,5,1,2), listOf(6,5,3,3,2), listOf(3,3,5,4,9), listOf(3,5,3,9,0))
        assertEquals(5, treehouse.countNumberOfVisibleInnerTrees())
    }

    @Test
    fun countNumberOfAllVisibleTreesTest() {
        treehouse.treeMap = listOf(listOf(3,0,3,7,3), listOf(2,5,5,1,2), listOf(6,5,3,3,2), listOf(3,3,5,4,9), listOf(3,5,3,9,0))
        val innerTrees = treehouse.countNumberOfVisibleInnerTrees()
        val edgeTrees = treehouse.countEdgeTrees()
        assertEquals(21, treehouse.countNumberOfAllVisibleTrees(innerTrees, edgeTrees))
    }

    @Test
    fun ifTopEdgeReturnZeroScenicScore() {
        treehouse.treeMap = listOf(listOf(3,0,3,7,3), listOf(2,5,5,1,2), listOf(6,5,3,3,2), listOf(3,3,5,4,9), listOf(3,5,3,9,0))
        assertEquals(0, treehouse.getScenicScore(0, 0))
    }

    @Test
    fun ifBottomEdgeReturnZeroScenicScore() {
        treehouse.treeMap = listOf(listOf(3,0,3,7,3), listOf(2,5,5,1,2), listOf(6,5,3,3,2), listOf(3,3,5,4,9), listOf(3,5,3,9,0))
        assertEquals(0, treehouse.getScenicScore(4, 2))
    }

    @Test
    fun ifRightEdgeReturnZeroScenicScore() {
        treehouse.treeMap = listOf(listOf(3,0,3,7,3), listOf(2,5,5,1,2), listOf(6,5,3,3,2), listOf(3,3,5,4,9), listOf(3,5,3,9,0))
        assertEquals(0, treehouse.getScenicScore(2, 4))
    }

    @Test
    fun getUnblockedViewToLeftTest() {
        treehouse.treeMap = listOf(listOf(3,0,3,7,3), listOf(2,5,5,1,2), listOf(6,5,3,3,2), listOf(3,3,5,4,9), listOf(3,5,3,9,0))
        assertEquals(1, treehouse.getUnblockedViewToLeft(1, 2))
    }

    @Test
    fun getUnblockedViewToRightTest() {
        treehouse.treeMap = listOf(listOf(3,0,3,7,3), listOf(2,5,5,1,2), listOf(6,5,3,3,2), listOf(3,3,5,4,9), listOf(3,5,3,9,0))
        assertEquals(2, treehouse.getUnblockedViewToRight(1, 2))
    }

    @Test
    fun getUnblockedViewUpTest() {
        treehouse.treeMap = listOf(listOf(3,0,3,7,3), listOf(2,5,5,1,2), listOf(6,5,3,3,2), listOf(3,3,5,4,9), listOf(3,5,3,9,0))
        assertEquals(1, treehouse.getUnblockedViewUp(1, 2))
    }

    @Test
    fun getUnblockedViewDownTest() {
        treehouse.treeMap = listOf(listOf(3,0,3,7,3), listOf(2,5,5,1,2), listOf(6,5,3,3,2), listOf(3,3,5,4,9), listOf(3,5,3,9,0))
        assertEquals(2, treehouse.getUnblockedViewDown(1, 2))
    }


}