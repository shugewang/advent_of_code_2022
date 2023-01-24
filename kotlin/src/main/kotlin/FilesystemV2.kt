import java.io.File

class FilesystemV2 (private val fileName: String) {
    private val directories = extractDirectories()

    fun extractDirectories(): List<String> {
        var input =  File(fileName).readLines().filter { it.contains("$ cd ") }.filterNot { it.contains("..") }
        return input.map { it.split(" ").takeLast(1).joinToString()}
    }
    fun separateIntoBlocksOfDirectories(): List<List<String>> {
        var input = File(fileName).readLines().filterNot { it.contains("..") }
        var blocks = mutableListOf(mutableListOf<String>())
        for (item in input) {
            if (item.contains("$ cd")) {
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
            if (map.contains(list[0].split(" ").takeLast(1).joinToString(""))) {
                println ("duplicate: " + list[0].split(" ").takeLast(1).joinToString(""))
            }
            map[list[0].split(" ").takeLast(1).joinToString("")] = list.subList(2,list.size)
        }
        return map
    }



    fun createDirectory(dir: Directory, filesystemMap: MutableMap<String, List<String>>): Directory {
        for (value in filesystemMap[dir.name]!!) {
//            println(dir.name + " has item " +value)

            if (value.contains("dir")) {
//                println("contains " + value)
                dir.dirContent.add(createDirectory(Directory(value.split(" ")[1]), filesystemMap))
            } else {
                dir.fileContent.add(FileItem(value.split(" ")[1], value.split(" ")[0].toInt()))
            }
        }
        return dir
    }

    fun getSizeOfDirectories(dir: Directory): Directory {
        for (file in dir.fileContent) {
            dir.addToSize(file.size)
        }
        for (i in dir.dirContent) {
            if (i is Directory) {
                getSizeOfDirectories(i)
                dir.addToSize(i.size)
            }
        }

        return dir

    }
    var solution = mutableListOf<Int>()
    fun printDirectory(dir: Directory): MutableList<Int> {
//        println("- ${dir.name} (dir) size ${dir.size}")
        if (dir.size < 100000) {
//            println ("${dir.name} has size < 100000")
            solution.add(dir.size)
        }
        for (file in dir.fileContent) {
//            println("${file.name} (file, size=${file.size})")
        }
        for (i in dir.dirContent) {
            if (i is Directory) {
                printDirectory(i)
            } else {
//                println(dir.name + " has dir " + i.name)
            }
        }
        return solution
    }

}

class Directory (val name: String) {
    var dirContent = mutableListOf<Directory>()
    var fileContent = mutableListOf<FileItem>()
    var size = 0
    fun addToSize(fileSize: Int) {
        size += fileSize
    }

}

class FileItem (val name: String, val size: Int)

fun main() {
    val filesystem = FilesystemV2("filesystem.txt")
    var filesystemMap = filesystem.createMapOfDirectoriesAndContent()
    println("filesystem:$filesystemMap")
    var root = Directory("/")
    var nestedDirs = filesystem.createDirectory(root, filesystemMap)
    nestedDirs = filesystem.getSizeOfDirectories(nestedDirs)
    var solution = filesystem.printDirectory(nestedDirs)
    println(solution.sum())

}
