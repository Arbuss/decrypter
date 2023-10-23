package reader

import logger.BaseLogger

class ConsoleReader(private val logger: BaseLogger) {
    private val logTag = "ConsoleReader"
    private val console = System.console()

    fun readLine(): String {
        val string = console.readLine()
        logger.log(tag = logTag, message = "readLine = $string")
        return string
    }
}