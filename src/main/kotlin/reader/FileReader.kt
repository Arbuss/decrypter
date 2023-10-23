package reader

import logger.BaseLogger
import java.io.File
import java.io.InputStream

class FileReader(private val logger: BaseLogger) {
    private val logTag = "FileReader"
    fun read(path: String): String {
        val inputStream: InputStream = File(path).inputStream()
        return inputStream.bufferedReader(charset = Charsets.UTF_8).use {
            val string = it.readText()
            logger.log(tag = logTag, message = string)
            string
        }
    }
}