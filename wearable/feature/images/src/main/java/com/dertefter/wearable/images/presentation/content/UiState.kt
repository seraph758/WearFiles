package com.dertefter.wearable.images.presentation.content

import com.dertefter.wearable.images.data.MediaItem

sealed interface UiState {
    data object Loading : UiState
    data class Success(
        val mediaItems: List<MediaItem>
    ) : UiState
    data class Failed (
        val e: Throwable
    ) : UiState
}