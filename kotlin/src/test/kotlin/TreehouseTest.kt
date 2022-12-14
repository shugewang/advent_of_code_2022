import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TreehouseTest {
    var treehouse = Treehouse("tree_height_map.txt")

    @Test
    fun convertInputTo2DListTest() {
        val expected = listOf(listOf(3,0,3,7,3), listOf(2,5,5,1,2), listOf(6,5,3,3,2), listOf(3,3,5,4,9), listOf(3,5,3,9,0))
        assertEquals(expected, treehouse.convertMapTo2DList())
    }

    @Test
    fun countNumberOfEdgeTrees() {
        treehouse.convertMapTo2DList()
        assertEquals(16, treehouse.countEdgeTrees())
    }

    @Test
    fun checkIfVisibleRowTrueTest() {
        treehouse.convertMapTo2DList()
        assertEquals(true, treehouse.checkIfVisibleByRow(2, 1))
    }

    @Test
    fun checkIfVisibleRowFalseTest() {
        treehouse.convertMapTo2DList()
        assertEquals(false, treehouse.checkIfVisibleByRow(2, 2))
    }

    @Test
    fun checkIfVisibleColTrueTest() {
        treehouse.convertMapTo2DList()
        assertEquals(true, treehouse.checkIfVisibleByCol(1, 1))
    }

    @Test
    fun checkIfVisibleColFalseTest() {
        treehouse.convertMapTo2DList()
        assertEquals(false, treehouse.checkIfVisibleByCol(3, 1))
    }

    @Test
    fun checkIfVisibleRowAndColTest() {
        treehouse.convertMapTo2DList()
        assertEquals(true, treehouse.checkIfVisible(1,2))
    }

    @Test
    fun countNumberOfVisibleInnerTreesTest() {
        treehouse.convertMapTo2DList()
        assertEquals(5, treehouse.countNumberOfVisibleInnerTrees())
    }

    @Test
    fun countNumberOfAllVisibleTreesTest() {
        treehouse.convertMapTo2DList()
        val innerTrees = treehouse.countNumberOfVisibleInnerTrees()
        val edgeTrees = treehouse.countEdgeTrees()
        assertEquals(21, treehouse.countNumberOfAllVisibleTrees(innerTrees, edgeTrees))
    }
}