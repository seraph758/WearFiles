package com.dertefter.wearable.image_viewer.presentation

import androidx.compose.runtime.Composable
import com.dertefter.wearable.design.components.basic_screens.ContentLoadingDefaultScreen
import com.dertefter.wearable.image_viewer.presentation.content.ContentSuccess

@Composable
fun ImageViewerScreen(uiState: UiState) {

    when(uiState){
        is UiState.Loading -> ContentLoadingDefaultScreen()
        is UiState.Success -> ContentSuccess(uiState.uriString)
        is UiState.Failed -> {

        }
     }

}
