package decrypter

import model.Dictionary

class Decrypter {
    companion object {
        fun decrypt(encryptedString: String, digitsNumber: Int, dictionary: Dictionary): DecryptionResult {
            val chunks = encryptedString.chunked(digitsNumber)

            var result = ""
            var errorCount = 0

            chunks.forEach {
                val decryptedLetter = dictionary.get(it)
                if (decryptedLetter != null) {
                    result += dictionary.get(it)
                } else {
                    errorCount++
                }
            }

            return DecryptionResult(text = result, errorCount = errorCount)
        }
    }
}