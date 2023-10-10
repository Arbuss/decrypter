import decrypter.Decrypter
import model.Dictionary
import reader.Reader

fun main() {
    val reader = Reader()

    println("Введите название файла словаря")
    val path = readln()

    val keyValueMap = reader.read("dictionary/$path").split(";").associate {
        val list = it.split("-")
        list.first().trim() to list.last().trim()
    }
    val dictionary = Dictionary(keyValueStore = keyValueMap)

    println("Введите зашифрованную строку")
    val encryptedString = readln()

    val decryptedString = Decrypter.decrypt(encryptedString = encryptedString, digitsNumber = dictionary.digitsNumber, dictionary)

    println("Расшифрованная строка: $decryptedString")
}