package jp.co.soramitsu.androidfoundation.compose

import android.annotation.SuppressLint
import androidx.compose.ui.Modifier

@Suppress("FunctionName")
@SuppressLint("ModifierFactoryExtensionFunction")
inline fun SwitchTo(modifier: Modifier, inCase: () -> Boolean) =
    if (inCase()) modifier else Modifier

const val ALWAYS: Boolean = true

@Suppress("NOTHING_TO_INLINE")
inline fun Modifier.chain(vararg modifiers: Modifier): Modifier =
    modifiers.fold(this) { acc, modifier -> acc then modifier }
