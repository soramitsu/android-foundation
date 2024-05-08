package jp.co.soramitsu.androidfoundation.format

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

inline fun <T> CoroutineScope.lazyAsync(crossinline producer: suspend () -> T) = lazy {
    async { producer() }
}
