package com.dertefter.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dertefter.data.repository.PinnedRepository
import com.dertefter.home.data.model.HomeItem
import com.dertefter.home.data.model.HomeItemType
import com.dertefter.home.presentation.MenuState.*
import com.dertefter.home.usecase.NavigateToGalleryUseCase
import com.dertefter.home.usecase.NavigateToMusicUseCase
import com.dertefter.home.usecase.NavigateToPathUseCase
import com.dertefter.home.usecase.NavigateToStorageUseCase
import com.dertefter.home.usecase.OpenFileUseCase
import com.dertefter.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigateToGalleryUseCase: NavigateToGalleryUseCase,
    private val navigateToMusicUseCase: NavigateToMusicUseCase,
    private val navigateToStorageUseCase: NavigateToStorageUseCase,
    private val settingsRepository: PinnedRepository,
    private val navigateToPathUseCase: NavigateToPathUseCase,
    private val openFileUseCase: OpenFileUseCase
) : ViewModel() {


    var state by mutableStateOf<UiState>(UiState.Loading)
        private set

    var menuState by mutableStateOf<MenuState>(MenuState.Hide)
        private set

    init {
        viewModelScope.launch {
            settingsRepository.getPinnedFlow().collect { pinned ->
                state = UiState.Success(
                    homeItems = listOf(
                        HomeItem(HomeItemType.MEDIA, Routes.Gallery),
                        HomeItem(HomeItemType.AUDIO, Routes.Music),
                        HomeItem(HomeItemType.STORAGE, Routes.FilesList())
                    ),
                    pinnedItems = pinned
                )
            }
        }
    }


    fun onEvent(event: Event) {
        when (event) {
            is Event.OnNavigateToGallery -> {
                navigateToGalleryUseCase()
            }

            is Event.OnNavigateToMusic -> {
                navigateToMusicUseCase()
            }

            is Event.OnNavigateToStorage -> {
                navigateToStorageUseCase()
            }

            is Event.OnHideMenu -> {
                menuState = MenuState.Hide
            }

            is Event.OnShowMenuFor -> {
                menuState = Show(event.path, event.menuMode)
            }

            is Event.OnDirectoryClick -> {
                navigateToPathUseCase(event.path)
            }
            is Event.OnFileClick -> {
                openFileUseCase(event.path)
            }
        }
    }

}
