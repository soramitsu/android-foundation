package jp.co.soramitsu.androidfoundation.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun Any.toTitle(): String {
    return if (this is String) this else stringResource(this as Int)
}
