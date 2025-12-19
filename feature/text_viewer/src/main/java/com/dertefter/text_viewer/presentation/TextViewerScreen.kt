package com.dertefter.text_viewer.presentation

import androidx.compose.runtime.Composable
import com.dertefter.text_viewer.presentation.content.ContentFailed
import com.dertefter.text_viewer.presentation.content.ContentLoading
import com.dertefter.text_viewer.presentation.content.ContentSuccess
import com.dertefter.text_viewer.presentation.UiState

@Composable
fun TextViewerScreen(onEvent: (Event) -> Unit, uiState: UiState) {

    when(uiState){
        is UiState.Loading -> ContentLoading()
        is UiState.Success -> ContentSuccess(uiState.fileName, uiState.content)
        is UiState.Failed -> {
            ContentFailed(onEvent=onEvent)
        }
     }

}
