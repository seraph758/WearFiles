package com.dertefter.file_list.presentation.content

import com.dertefter.data.model.PrettyPath
import java.io.File

sealed interface UiState {
    data object Loading : UiState
    data class Success(val files: List<File>, val path: PrettyPath) : UiState
    data object Failed : UiState
}