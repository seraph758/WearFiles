package com.dertefter.menu.presentation

import androidx.compose.runtime.Composable
import com.dertefter.menu.presentation.content.ContentLoading
import com.dertefter.menu.presentation.content.ContentSuccess
import com.dertefter.menu.presentation.content.UiState

@Composable
fun MenuScreen(onEvent: (Event) -> Unit, uiState: UiState) {

    when(uiState){
        is UiState.Loading -> ContentLoading()
        is UiState.Success -> ContentSuccess(
            name = uiState.name,
            actions = uiState.actions,
            onEvent = onEvent
            )
        is UiState.Failed -> {
            onEvent(Event.OnNavigateBack)
        }
     }

}
