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
            map[list[0]] = list.subList(2,list.size)
        }

        return map
    }

    class Directory (var name: String, var content: List<String>){


    }


}

