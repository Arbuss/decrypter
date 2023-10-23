import command.CommandHandler
import config.Config
import decrypter.Decrypter
import logger.BaseLogger
import logger.FileLogger
import model.Dictionary
import printer.Printer
import printer.TerminalPrinter
import reader.ConsoleReader
import reader.FileReader


fun main() {
    val logger: BaseLogger = FileLogger()

    val fileReader = FileReader(logger = logger)
    val consoleReader = ConsoleReader(logger = logger)
    val printer: Printer = TerminalPrinter(logger = logger)
    val commandHandler = CommandHandler(logger = logger, printer = printer)

    printer.print("Введите название файла словаря")
    val path = consoleReader.readLine()
    commandHandler.handle(path)

    val dictionaryList = fileReader.read(path = "dictionary/$path")
        .replace("\n", "")
        .replace("\r", "").split("|")
    val keyValueMap = dictionaryList.associate {
        val list = it.split("'")
        list.first() to list.last()
    }
    val keyValueMapReverted = dictionaryList.associate {
        val list = it.split("'")
        list.last() to list.first()
    }
    val dictionary =
        Dictionary(keyValueStore = keyValueMap, keyValueStoreReverted = keyValueMapReverted, logger = logger)

    val decrypter = Decrypter(dictionary = dictionary, logger = logger)

    while (true) {
        printer.print("Введите зашифрованную строку")
        val encryptedString = consoleReader.readLine()

        if (commandHandler.handle(encryptedString)) {
            continue
        }

        if (encryptedString.length.mod(dictionary.digitsNumber) != 0) {
            printer.print("Длина не соответствует порядку алфавита, длина = ${encryptedString.length}, порядок = ${dictionary.digitsNumber}")
        }

        val decryptionResult =
            decrypter.decrypt(encryptedString = encryptedString, isReverted = Config.revertDictionary)

        printer.print("Расшифрованная строка: ${decryptionResult.text}")
        printer.print("Количество ошибок: ${decryptionResult.errorCount}")
    }
}