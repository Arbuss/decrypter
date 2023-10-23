package decrypter

import logger.BaseLogger
import model.Dictionary

class Decrypter(
    private val dictionary: Dictionary,
    private val logger: BaseLogger
) {
    val logTag = "Decrypter"

    fun decrypt(encryptedString: String, isReverted: Boolean): DecryptionResult {
        logger.log(tag = logTag, message = "isReverted = $isReverted")
        logger.log(tag = logTag, message = "encryptedString = $encryptedString")
        val chunks = encryptedString.chunked(dictionary.digitsNumber)

        var result = ""
        var errorCount = 0

        chunks.forEach {
            val decryptedLetter = dictionary.get(key = it, isReverted = isReverted)
            logger.log(tag = logTag, message = "decryptedLetter = $decryptedLetter")
            if (decryptedLetter != null) {
                result += decryptedLetter
            } else {
                errorCount++
            }
        }

        return DecryptionResult(text = result, errorCount = errorCount)
    }
}