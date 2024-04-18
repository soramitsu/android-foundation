package jp.co.soramitsu.androidfoundation.format

fun String.nullIfEmpty(): String? = ifEmpty { null }

const val HEX_PREFIX = "0x"

fun String.removeHexPrefix() = this.removePrefix(HEX_PREFIX)
