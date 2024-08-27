package jp.co.soramitsu.androidfoundation.format

import java.io.InputStream

fun InputStream.readToString(): String {
    return this.bufferedReader().use { it.readText() }
}
