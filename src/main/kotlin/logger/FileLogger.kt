package logger

import java.io.BufferedWriter
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class FileLogger : BaseLogger {
    private lateinit var logFile: File
    private lateinit var buffer: BufferedWriter
    private val dirName = "log"

    init {
        init()
    }

    override fun log(tag: String, message: String) {
        buffer.append(compileMessage(tag = tag, message = message))
        buffer.newLine()
        buffer.flush()
    }

    private fun init() {
        val directory = File(dirName)
        if (!directory.exists()) {
            directory.mkdir()
        }

        val fileName = generateFileName()
        val file = File(dirName, fileName)

        if (!file.exists()) {
            println(fileName)
            file.createNewFile()
        }

        logFile = file
        buffer = logFile.bufferedWriter()
    }

    private fun generateFileName(): String {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd.MM.yyyy hh.mm.ss")
        return "${formatter.format(time)}.log"
    }

    private fun compileMessage(tag: String, message: String): String {
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        val time = formatter.format(date)

        val thread = Thread.currentThread().name

        return "[$time]  [$thread]  [$tag]: $message"
    }
}