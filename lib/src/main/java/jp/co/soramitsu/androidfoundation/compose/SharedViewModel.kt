package jp.co.soramitsu.androidfoundation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavController,
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry =
        remember(this) {
            navController.getBackStackEntry(navGraphRoute)
        }
    return viewModel(parentEntry)
}

@Composable
inline fun <reified T : ViewModel, reified VMF> NavBackStackEntry.sharedHiltViewModelWithCallback(
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

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedHiltViewModel(
    navController: NavController,
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry =
        remember(this) {
            navController.getBackStackEntry(navGraphRoute)
        }
    return hiltViewModel(parentEntry)
}
