package com.dertefter.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dertefter.home.data.model.Pinned
import com.dertefter.home.data.model.PinnedType
import com.dertefter.home.usecase.NavigateToGalleryUseCase
import com.dertefter.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigateToGalleryUseCase: NavigateToGalleryUseCase
) : ViewModel() {

    var state by mutableStateOf<UiState>(
        UiState.Success(
            pinned = listOf(
                Pinned(
                    PinnedType.MEDIA,
                    Routes.OnBoarding
                )
            )
        )
    )
        private set


    fun onEvent(event: Event) {
        when (event) {
            is Event.OnNavigateToGallery -> {
                navigateToGalleryUseCase()
            }

        }
    }

}
