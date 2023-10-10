import decrypter.Decrypter
import model.Dictionary
import reader.Reader

const val EXIT_COMMAND = "exit"

fun main() {
    val reader = Reader()

    println("Введите название файла словаря")
    val path = readln()

    val keyValueMap = reader.read("dictionary/$path")
        .replace("\n", "")
        .replace("\r", "").split("|").associate {
            val list = it.split("'")
            list.first() to list.last()
        }
    val dictionary = Dictionary(keyValueStore = keyValueMap)

    var commandString = ""
    while (commandString != EXIT_COMMAND) {
        println("Введите зашифрованную строку")
        val encryptedString = readln()
        if (encryptedString.lowercase() == EXIT_COMMAND) {
            commandString = encryptedString
        } else {
            if (encryptedString.length.mod(dictionary.digitsNumber) != 0) {
                println("Длина не соответствует порядку алфавита, длина = ${encryptedString.length}, порядок = ${dictionary.digitsNumber}")
            }

            val decryptionResult =
                Decrypter.decrypt(encryptedString = encryptedString, digitsNumber = dictionary.digitsNumber, dictionary)

            println("Расшифрованная строка: ${decryptionResult.text}")
            println("Количество ошибок: ${decryptionResult.errorCount}")
        }
    }
    println("Программа завершена")
}