package jp.co.soramitsu.androidfoundation.compose

import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf

@Stable
sealed interface UiState {
    @Stable
    data object Loading : UiState

    @Stable
    data object Error : UiState

    @Stable
    data object Rendering : UiState

    @Stable
    data object Initial : UiState
}

val LocalUiState = staticCompositionLocalOf { UiState.Initial }
