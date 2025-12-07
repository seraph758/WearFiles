package com.dertefter.menu.presentation.content

import com.dertefter.menu.presentation.MenuAction

sealed interface UiState {
    data object Loading : UiState
    data class Success(
        val name: String,
        val actions: List<MenuAction> = emptyList()
    ) : UiState
    data class Failed (
        val e: Throwable
    ) : UiState
}