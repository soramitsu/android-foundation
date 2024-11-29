package jp.co.soramitsu.androidfoundation.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class CoroutineManager {
    val applicationScope = CoroutineScope(SupervisorJob())
    val io = Dispatchers.IO
    val main = Dispatchers.Main
    val default = Dispatchers.Default
    val unconfined = Dispatchers.Unconfined

    fun createSupervisorScope() = CoroutineScope(Dispatchers.IO + SupervisorJob())
}
