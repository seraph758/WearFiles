package com.dertefter.menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.dertefter.menu.presentation.Event
import com.dertefter.menu.presentation.MenuScreen
import com.dertefter.menu.presentation.MenuViewModel

@Composable
fun MenuRoute(
    viewModel: MenuViewModel = hiltViewModel(),
    paths: List<String>,
    onDismissRequest: () -> Unit = {},
    mode: MenuMode = MenuMode.OUTSIDE
) {

    val uiState = viewModel.state

    LaunchedEffect(paths) {
        viewModel.onEvent(Event.OnGetMenuActions(paths, mode))
        viewModel.setOnDismiss(onDismissRequest)
    }

    MenuScreen(
        uiState = uiState, onEvent = { event ->
            viewModel.onEvent(event)
        })

}

enum class MenuMode {
    INSIDE, OUTSIDE, PINNED
}
