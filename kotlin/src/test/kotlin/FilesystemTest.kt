import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FilesystemTest {
    private val filesystem = Filesystem("filesystem.txt")
    @Test
    fun extractDirectoriesTest() {
        val expected = listOf("/", "a", "e", "d")
        assertEquals(expected, filesystem.extractDirectories())
    }

//    @Test
//    fun createMapOfDirectoriesAndContentTest() {
//        val expected = mapOf<Item, Item>()
//        assertEquals(expected, filesystem.createDirectoriesMap())
//    }


}