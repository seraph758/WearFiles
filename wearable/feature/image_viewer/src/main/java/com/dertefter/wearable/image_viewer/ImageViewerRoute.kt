package com.dertefter.wearable.image_viewer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.dertefter.wearable.image_viewer.presentation.ImageViewerScreen
import com.dertefter.wearable.image_viewer.presentation.ImageViewerViewModel

@Composable
fun ImageViewerRoute(
    uriString: String,
) {

    val viewModel: ImageViewerViewModel = hiltViewModel()

    val uiState = viewModel.state


    LaunchedEffect(uriString) {
        viewModel.setUriString(uriString)
    }

    ImageViewerScreen(
        uiState = uiState
    )

}