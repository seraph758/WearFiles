package com.dertefter.file_list.presentation

import androidx.compose.runtime.Composable
import com.dertefter.file_list.presentation.content.ContentLoading
import com.dertefter.file_list.presentation.content.ContentSuccess
import com.dertefter.file_list.presentation.content.UiState

@Composable
fun FileListScreen(onEvent: (Event) -> Unit, uiState: UiState) {

    when(uiState){
        is UiState.Loading -> ContentLoading()
        is UiState.Success -> ContentSuccess(uiState.files, onEvent)
        is UiState.Failed -> {}
     }

}
