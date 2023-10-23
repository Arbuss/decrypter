package printer

import logger.BaseLogger

class TerminalPrinter(private val logger: BaseLogger) : Printer {
    private val logTag = "Printer"

    override fun print(message: String) {
        logger.log(logTag, message)
        println(message)
    }
}