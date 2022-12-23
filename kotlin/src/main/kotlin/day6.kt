import java.io.File

private fun findFirstInstanceOfUniqueChars(datastream: String, length: Int): Int? {
    for (i in length..datastream.indices.max()) {
        val fourCharArray = datastream.substring(i - length, i).split("").drop(1).dropLast(1)
        if (fourCharArray.distinct().size == length) {
            println("${datastream.substring(i - length, i)} is unique")
            return i
        }
    }
    return null
}

fun findStartOfPacketMarker(datastream: String): Int? {
    return findFirstInstanceOfUniqueChars(datastream, 4)
}

fun findStartOfMessageMarker(datastream: String): Int? {
    return findFirstInstanceOfUniqueChars(datastream, 14)
}

fun main() {
    val datastream = File("datastream_buffer.txt").readText()
    println("datastream: $datastream")
    val startOfPacketMarker = findStartOfPacketMarker(datastream)
    println("start-of-packet marker is $startOfPacketMarker")
    val startOfMessageMarker = findStartOfMessageMarker(datastream)
    println("start-of-message marker is $startOfMessageMarker")
}