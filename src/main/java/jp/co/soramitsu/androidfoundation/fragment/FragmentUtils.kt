package jp.co.soramitsu.androidfoundation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment

inline fun <T : androidx.fragment.app.Fragment> T.withArgs(argInitializer: (arguments: Bundle) -> Unit): T {
    val args = arguments ?: Bundle()
    argInitializer.invoke(args)
    arguments = args
    return this
}

inline fun <T : androidx.fragment.app.Fragment> T.applyArgs(argInitializer: Bundle.() -> Unit): T {
    arguments = (arguments ?: Bundle()).apply(argInitializer)
    return this
}
