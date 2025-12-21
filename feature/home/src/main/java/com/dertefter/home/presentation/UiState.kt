package com.dertefter.home.presentation

import com.dertefter.data.model.PinnedItem
import com.dertefter.home.data.model.HomeItem

sealed interface UiState {
    data object Loading : UiState
    data class Success(
        val homeItems: List<HomeItem>,
        val pinnedItems: List<PinnedItem>
    ) : UiState
    data class Failed (
        val e: Throwable
    ) : UiState
}