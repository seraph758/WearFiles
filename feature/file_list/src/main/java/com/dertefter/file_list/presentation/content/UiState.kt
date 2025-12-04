package com.dertefter.file_list.presentation.content

import java.io.File

sealed interface UiState {
    data object Loading : UiState
    data class Success(val files: List<File>) : UiState
    data object Failed : UiState
}