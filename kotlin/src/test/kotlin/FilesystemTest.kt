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
//    fun createListOfDirectoryClassesTest() {
////        val expected = listOf(Filesystem.Directory("/"), Filesystem.Directory("a"), Filesystem.Directory("e"), Filesystem.Directory("d"))
//        val expected = listOf("/", "a", "e", "d")
//        val actual = mutableListOf<String>()
//        for (directory in filesystem.createListOfDirectoryClasses()) {
//            actual.add(directory.name)
//        }
//        assertEquals(expected, actual)
//    }

    @Test
    fun separateBlockOfDirectoriesAndTheirContentsTest() {
        val expected = listOf(listOf("$ cd /", "$ ls", "dir a", "14848514 b.txt", "8504156 c.dat", "dir d"), listOf("$ cd a", "$ ls", "dir e", "29116 f", "2557 g", "62596 h.lst"), listOf("$ cd e", "$ ls", "584 i"), listOf("$ cd d", "$ ls", "4060174 j", "8033020 d.log", "5626152 d.ext", "7214296 k"))
        assertEquals(expected, filesystem.separateIntoBlocksOfDirectories())
    }

    @Test
    fun createMapOfDirectoriesTest() {
        val expected = mutableMapOf("/" to listOf("dir a", "14848514 b.txt", "8504156 c.dat", "dir d"), "a" to listOf("dir e", "29116 f", "2557 g", "62596 h.lst"), "e" to listOf("584 i"), "d" to listOf("4060174 j", "8033020 d.log", "5626152 d.ext", "7214296 k"))
        assertEquals(expected, filesystem.createMapOfDirectoriesAndContent())
    }

    @Test
    fun createFileClasses() {
        val expected = mutableListOf<Filesystem.Directory>(Filesystem.Directory("/"), Filesystem.Directory("a"), Filesystem.Directory("e"), Filesystem.Directory("d"))
        assertEquals(expected, filesystem.convertFileStringIntoClasses())
    }


}