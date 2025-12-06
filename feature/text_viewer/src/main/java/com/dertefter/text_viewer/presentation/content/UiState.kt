package com.dertefter.text_viewer.presentation.content

import java.io.File

sealed interface UiState {
    data object Loading : UiState
    data class Success(val content: String?) : UiState
    data object Failed : UiState
}