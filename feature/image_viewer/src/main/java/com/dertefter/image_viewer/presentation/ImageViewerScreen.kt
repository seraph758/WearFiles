package com.dertefter.image_viewer.presentation

import androidx.compose.runtime.Composable
import com.dertefter.design.components.basic_screens.ContentLoadingDefaultScreen
import com.dertefter.image_viewer.presentation.content.ContentSuccess

@Composable
fun ImageViewerScreen(uiState: UiState) {

    when(uiState){
        is UiState.Loading -> ContentLoadingDefaultScreen()
        is UiState.Success -> ContentSuccess(uiState.uriString)
        is UiState.Failed -> {

        }
     }

}
