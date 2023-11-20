package jp.co.soramitsu.androidfoundation.format

fun String.nullIfEmpty(): String? = ifEmpty { null }
