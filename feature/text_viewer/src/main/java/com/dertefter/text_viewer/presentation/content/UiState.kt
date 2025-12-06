package com.dertefter.text_viewer.presentation.content

sealed interface UiState {
    data object Loading : UiState
    data class Success(val content: String?, val fileName: String) : UiState
    data object Failed : UiState
}