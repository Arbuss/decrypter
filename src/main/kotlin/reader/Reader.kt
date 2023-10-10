package reader

import java.io.File
import java.io.InputStream

class Reader {
    fun read(path: String): String {
        val inputStream: InputStream = File(path).inputStream()
        return inputStream.bufferedReader().use { it.readText() }
    }
}