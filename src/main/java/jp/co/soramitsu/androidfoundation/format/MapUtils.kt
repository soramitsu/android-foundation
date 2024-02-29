package jp.co.soramitsu.androidfoundation.format

inline fun <K, V, R> Map<out K, V>.mapNotNullKeys(transform: (Map.Entry<K, V>) -> R?): Map<R, V> {
    val result = LinkedHashMap<R, V>()
    for (entry in this) {
        val t = transform(entry)
        if (t != null) {
            result[t] = entry.value
        }
    }
    return result
}
