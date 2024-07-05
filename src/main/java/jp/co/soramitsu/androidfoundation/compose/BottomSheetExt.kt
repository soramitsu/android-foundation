package jp.co.soramitsu.androidfoundation.compose

import android.app.Activity
import android.view.ViewGroup
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import kotlinx.coroutines.launch

fun Activity.showAsBottomSheet(content: @Composable (() -> Unit) -> Unit) {
    val viewGroup: ViewGroup = this.findViewById(android.R.id.content)
    addContentToView(viewGroup, content)
}

fun Fragment.showAsBottomSheet(content: @Composable (() -> Unit) -> Unit) {
    val viewGroup: ViewGroup = requireActivity().findViewById(android.R.id.content)
    addContentToView(viewGroup, content)
}

private fun addContentToView(
    viewGroup: ViewGroup,
    content: @Composable (() -> Unit) -> Unit
) {
    viewGroup.addView(
        ComposeView(viewGroup.context).apply {
            setContent {
                BottomSheetWrapper(viewGroup, this, content)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomSheetWrapper(
    parent: ViewGroup,
    composeView: ComposeView,
    content: @Composable (() -> Unit) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
        confirmValueChange = { _: SheetValue ->
            true
        },
    )
    var isSheetOpened by remember { mutableStateOf(false) }

    ModalBottomSheet(
        modifier = Modifier.fillMaxHeight(),
        onDismissRequest = { coroutineScope.launch { modalBottomSheetState.hide() } },
        sheetState = modalBottomSheetState,
        properties = ModalBottomSheetDefaults.properties(),
        dragHandle = {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .background(Color.Red)
            )
        },
        content = {
            content {
                coroutineScope.launch {
                    modalBottomSheetState.hide()
                }
            }
        }
    )

    BackHandler {
        coroutineScope.launch {
            modalBottomSheetState.hide()
        }
    }

    LaunchedEffect(modalBottomSheetState.currentValue) {
        when (modalBottomSheetState.currentValue) {
            SheetValue.Hidden -> {
                when {
                    isSheetOpened -> parent.removeView(composeView)
                    else -> {
                        isSheetOpened = true
                        modalBottomSheetState.show()
                    }
                }
            }

            else -> {
            }
        }
    }
}
