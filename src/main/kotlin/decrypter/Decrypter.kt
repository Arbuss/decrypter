package decrypter

import model.Dictionary

class Decrypter {
    companion object {
        fun decrypt(encryptedString: String, digitsNumber: Int, dictionary: Dictionary): String {
            val chunks = encryptedString.chunked(digitsNumber)

            var result = ""

            chunks.forEach {
                result += dictionary.get(it)
            }

            return result
        }
    }
}