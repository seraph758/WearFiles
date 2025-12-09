package com.dertefter.delete.presentation

sealed interface UiState {
    data object Loading : UiState
    data class Success(
        val path: String,
    ) : UiState

    data class Failed (
        val e: Throwable
    ) : UiState
}