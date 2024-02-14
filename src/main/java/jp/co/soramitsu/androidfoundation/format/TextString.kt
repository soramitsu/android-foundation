package jp.co.soramitsu.androidfoundation.format

fun String.nullIfEmpty(): String? = ifEmpty { null }

const val hexPrefix = "0x"

fun String.removeHexPrefix() = this.removePrefix(hexPrefix)
