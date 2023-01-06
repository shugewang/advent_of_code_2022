import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FilesystemTest {
    private val filesystem = Filesystem("filesystem.txt")
    @Test
    fun keepOnlyChangeDirLinesTest() {
        val expected = listOf("$ cd /", "$ cd a", "$ cd e", "$ cd d")
        assertEquals(expected, filesystem.getChangeDirLines())
    }

    @Test
    fun createMapOfDirectoriesAndContentTest() {
        val expected = mapOf<Item, Item>()
        assertEquals(expected, filesystem.createDirectoriesMap())
    }


}