package com.dertefter.home.presentation

import com.dertefter.home.data.model.Pinned

sealed interface UiState {
    data object Loading : UiState
    data class Success(
        val pinned: List<Pinned>
    ) : UiState
    data class Failed (
        val e: Throwable
    ) : UiState
}