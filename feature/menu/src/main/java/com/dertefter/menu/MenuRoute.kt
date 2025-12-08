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
    path: String,
    onDismissRequest: () -> Unit = {},
    mode: MenuMode = MenuMode.OUTSIDE
) {

    val uiState = viewModel.state

    LaunchedEffect(path) {
       viewModel.onEvent(Event.OnGetMenuActions(path,mode))
    }

    MenuScreen(
        uiState = uiState,
        onEvent = { event ->
            viewModel.onEvent(event)
            onDismissRequest()
        }
    )

}

enum class MenuMode{
    INSIDE, OUTSIDE
}
