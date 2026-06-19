package com.dertefter.wearable.home.presentation

import com.dertefter.wearable.data.model.PinnedItem
import com.dertefter.wearable.home.model.HomeItem

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