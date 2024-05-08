package jp.co.soramitsu.androidfoundation.format

import java.math.BigInteger

fun <K, V> Map<K, V>.inverseMap() = map { Pair(it.value, it.key) }.toMap()

inline fun <T> Iterable<T>.sumByBigInteger(selector: (T) -> BigInteger): BigInteger {
    var sum: BigInteger = BigInteger.ZERO
    for (element in this) {
        sum += selector(element)
    }
    return sum
}

fun <T> List<T>.cycle(): Sequence<T> {
    if (isEmpty()) return emptySequence()

    var i = 0

    return generateSequence { this[i++ % this.size] }
}
