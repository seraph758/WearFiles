package com.dertefter.wearable.music

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.dertefter.wearable.music.presentation.Event
import com.dertefter.wearable.music.presentation.MusicViewModel
import com.dertefter.wearable.music.presentation.MusicScreen

@Composable
fun MusicRoute(
    viewModel: MusicViewModel = hiltViewModel(),
) {

    val uiState = viewModel.state
    val dialogState = viewModel.permissionDialogState

    LaunchedEffect(Unit) {
        viewModel.onEvent(Event.OnLoad)
    }

    MusicScreen(
        uiState = uiState,
        dialogState = dialogState,
        onEvent = { event ->
            viewModel.onEvent(event)
        },
    )

}
