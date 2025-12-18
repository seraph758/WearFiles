package com.dertefter.gallery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.dertefter.gallery.presentation.Event
import com.dertefter.gallery.presentation.GalleryViewModel
import com.dertefter.gallery.presentation.GalleyScreen

@Composable
fun GalleryRoute(
    viewModel: GalleryViewModel = hiltViewModel(),
) {

    val uiState = viewModel.state
    val dialogState = viewModel.permissionDialogState

    LaunchedEffect(Unit) {
        viewModel.onEvent(Event.OnLoad)
    }

    GalleyScreen(
        uiState = uiState,
        dialogState = dialogState,
        onEvent = { event ->
            viewModel.onEvent(event)
        },
    )

}
