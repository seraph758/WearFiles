package com.dertefter.wearable.home.presentation

import android.os.Environment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dertefter.wearable.data.repository.PinnedRepository
import com.dertefter.wearable.home.model.HomeItem
import com.dertefter.wearable.home.model.HomeItemType
import com.dertefter.wearable.home.usecase.NavigateToPathUseCase
import com.dertefter.wearable.home.usecase.NavigateToStorageUseCase
import com.dertefter.wearable.home.usecase.NavigateToUseCase
import com.dertefter.wearable.home.usecase.OpenFileUseCase
import com.dertefter.wearable.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigateToUseCase: NavigateToUseCase,
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
                        HomeItem(HomeItemType.IMAGES, Routes.Gallery),
                        HomeItem(HomeItemType.VIDEOS, Routes.Video),
                        HomeItem(HomeItemType.AUDIO, Routes.Music),
                        HomeItem(
                            HomeItemType.RECEIVED,
                            Routes.FilesList(
                                File(
                                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                                    "Received"
                                ).absolutePath
                            )
                        ),
                        HomeItem(HomeItemType.STORAGE, Routes.FilesList()),
                    ),
                    pinnedItems = pinned
                )
            }
        }
    }


    fun onEvent(event: Event) {
        when (event) {
            is Event.OnNavigateTo -> {
                navigateToUseCase(event.routes)
            }

            is Event.OnNavigateToStorage -> {
                navigateToStorageUseCase()
            }

            is Event.OnHideMenu -> {
                menuState = MenuState.Hide
            }

            is Event.OnShowMenuFor -> {
                menuState = MenuState.Show(event.paths, event.menuMode)
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
