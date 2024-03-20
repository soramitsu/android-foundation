package jp.co.soramitsu.androidfoundation.testing

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader

fun Any.getFileContentFromResources(fileName: String): String {
    return getResourceReader(fileName).readText()
}

fun Any.getResourceReader(fileName: String): Reader {
    val stream = getResourceAsStream(fileName)

    return BufferedReader(InputStreamReader(stream))
}

fun Any.getResourceAsStream(fileName: String): InputStream {
    return javaClass.classLoader!!.getResourceAsStream(fileName)
}