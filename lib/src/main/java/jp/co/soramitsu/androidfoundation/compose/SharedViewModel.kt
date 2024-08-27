package jp.co.soramitsu.androidfoundation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

@Composable
inline fun <reified T : ViewModel, reified VMF> NavBackStackEntry.sharedViewModel(
    navController: NavController,
    noinline creationCallback: (VMF) -> T,
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry =
        remember(this) {
            navController.getBackStackEntry(navGraphRoute)
        }
    return hiltViewModel(parentEntry, creationCallback = creationCallback)
}
