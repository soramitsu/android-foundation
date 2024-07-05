package jp.co.soramitsu.androidfoundation.testing

import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito

fun <T> anyNonNull(): T {
    Mockito.any<T>()
    return initialized()
}

fun <T : Any> eqNonNull(value: T): T = eq(value) ?: value

private fun <T> initialized(): T = null as T
