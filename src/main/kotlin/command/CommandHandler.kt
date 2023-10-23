package command

import config.Config
import logger.BaseLogger
import printer.Printer
import kotlin.system.exitProcess

class CommandHandler(private val logger: BaseLogger, private val printer: Printer) {
    private val logTag = "CommandHandler"

    fun handle(string: String): Boolean {

        return when (string) {
            Command.EXIT.textCommand -> {
                logger.log(logTag, "command = $string")
                exitProcess(0)
            }

            Command.REVERT.textCommand -> {
                logger.log(logTag, "command = $string")
                Config.revertDictionary = !Config.revertDictionary
                true
            }

            Command.HELP.textCommand -> {
                logger.log(logTag, "command = $string")
                Command.values().forEach {
                    printer.print(it.textCommand)
                }
                true
            }

            else -> {
                false
            }
        }
    }
}