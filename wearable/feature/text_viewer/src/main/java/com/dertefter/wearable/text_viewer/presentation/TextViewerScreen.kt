package com.dertefter.wearable.text_viewer.presentation

import androidx.compose.runtime.Composable
import com.dertefter.wearable.text_viewer.presentation.content.ContentFailed
import com.dertefter.wearable.text_viewer.presentation.content.ContentLoading
import com.dertefter.wearable.text_viewer.presentation.content.ContentSuccess

@Composable
fun TextViewerScreen(uiState: UiState) {

    when(uiState){
        is UiState.Loading -> ContentLoading()
        is UiState.Success -> ContentSuccess(uiState.fileName, uiState.content)
        is UiState.Failed -> {
            ContentFailed()
        }
     }

}
