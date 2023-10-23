package model

import logger.BaseLogger

data class Dictionary(
    private val keyValueStore: Map<String, String>,
    private val keyValueStoreReverted: Map<String, String>,
    private val logger: BaseLogger
) {

    val digitsNumber: Int
        get() = keyValueStore.keys.first().length

    fun get(key: String, isReverted: Boolean): String? {
        return if (isReverted) {
            keyValueStoreReverted[key]
        } else {
            keyValueStore[key]
        }
    }

    fun put(key: String, value: String): Dictionary {
        val map = keyValueStore.toMutableMap()
        map[key] = value
        return this.copy(keyValueStore = map)
    }

    fun fill(map: Map<String, String>) = this.copy(keyValueStore = map)
}
