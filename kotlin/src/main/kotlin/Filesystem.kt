// day 7
import java.io.File

class Filesystem (private val fileName: String) {
    private val directories = extractDirectories()
    fun extractDirectories(): List<String> {
        var input =  File(fileName).readLines().filter { it.contains("cd") }.filterNot { it.contains("..") }
        return input.map { it.split(" ").takeLast(1).joinToString()}
    }

//    fun createListOfDirectoryClasses(): MutableList<Directory> {
//
//        var directoriesList = mutableListOf<Directory>()
//
//        for (directory in directories) {
//            directoriesList.add(Directory(directory))
//        }
//        return directoriesList
//    }

    fun separateIntoBlocksOfDirectories(): List<List<String>> {
        var input = File(fileName).readLines().filterNot { it.contains("..") }
        var blocks = mutableListOf(mutableListOf<String>())
        for (item in input) {
            if (item.contains("cd")) {
                blocks.add(mutableListOf(item))
            } else {
                blocks[blocks.lastIndex].add(item)
            }
        }
        return blocks.drop(1)
    }

    fun createMapOfDirectoriesAndContent(): MutableMap<String, List<String>> {
        var input = separateIntoBlocksOfDirectories()
        var map = mutableMapOf<String, List<String>>()
        for (list in input) {
            map[list[0].split(" ").takeLast(1).joinToString("")] = list.subList(2,list.size)
        }
        return map
    }

    fun convertFileStringIntoClasses(): MutableList<Directory> {
        var mapOfDirectoriesAndContent = createMapOfDirectoriesAndContent()
        var listOfDirectoryClasses = mutableListOf<Directory>()
        for ((key, value) in mapOfDirectoriesAndContent) {
            listOfDirectoryClasses.add(Directory(key))
//            for (item in value) {
//                listOfDirectoryClasses[listOfDirectoryClasses.lastIndex].addToDirectoryContent(item)
//            }
        }
        return listOfDirectoryClasses
    }

    sealed class Item (val name: String)

    class Directory (name: String) : Item(name){
        private var content = mutableListOf<Any>()
        fun addToDirectoryContent(item: Item) {
            content.add(item)
        }

        override fun equals(other: Any?): Boolean {
            if (other == null) return false
            if (this === other) return true
            if (other !is Directory) return false
            if (name != other.name || content != other.content) return false
            return true
        }
    }

    class FileItem (){

    }


}

