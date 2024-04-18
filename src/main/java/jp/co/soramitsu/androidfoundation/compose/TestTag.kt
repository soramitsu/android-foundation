package jp.co.soramitsu.androidfoundation.compose

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.testTagAsId(main: String, tag: String): Modifier =
    semantics { testTagsAsResourceId = true }.testTag("$main/$tag")
