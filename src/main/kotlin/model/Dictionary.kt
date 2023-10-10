package model

data class Dictionary(
    private val keyValueStore: Map<String, String>
) {
    fun get(key: String) = keyValueStore[key]

    fun put(key: String, value: String): Dictionary {
        val map = keyValueStore.toMutableMap()
        map[key] = value
        return this.copy(keyValueStore = map)
    }

    fun fill(map: Map<String, String>) = this.copy(keyValueStore = map)
}
