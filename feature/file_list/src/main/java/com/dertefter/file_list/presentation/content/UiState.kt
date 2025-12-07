package com.dertefter.file_list.presentation.content

import com.dertefter.data.model.PrettyPath
import com.dertefter.file_list.presentation.Action
import java.io.File

sealed interface UiState {
    data object Loading : UiState
    data class Success(
        val files: List<File>,
        val path: PrettyPath,
        val actions: List<Action> = emptyList()
    ) : UiState
    data class Failed (
        val e: Throwable
    ) : UiState
}