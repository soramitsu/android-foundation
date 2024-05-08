package jp.co.soramitsu.androidfoundation.format

fun String.nullIfEmpty(): String? = ifEmpty { null }

const val HEX_PREFIX = "0x"

const val EURO_SIGN = '€'

fun String.removeHexPrefix() = this.removePrefix(HEX_PREFIX)
fun String.addHexPrefix(): String = "$HEX_PREFIX$this"

typealias StringPair = Pair<String, String>

typealias StringTriple = Triple<String, String, String>
