package com.dertefter.menu.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dertefter.menu.presentation.Event.OnDirectoryClick
import com.dertefter.menu.presentation.Event.OnFileClick
import com.dertefter.menu.presentation.content.UiState
import com.dertefter.menu.presentation.content.UiState.Success
import com.dertefter.menu.usecase.GetMenuActionsUseCase
import com.dertefter.menu.usecase.IsDirectoryUseCase
import com.dertefter.menu.usecase.NavigateBackUseCase
import com.dertefter.menu.usecase.NavigateToDeleteUseCase
import com.dertefter.menu.usecase.NavigateToNewDirectoryUseCase
import com.dertefter.menu.usecase.NavigateToPathUseCase
import com.dertefter.menu.usecase.NavigateToRenameUseCase
import com.dertefter.menu.usecase.OpenFileUseCase
import com.dertefter.menu.usecase.PinUseCase
import com.dertefter.menu.usecase.UnpinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val navigateBackUseCase: NavigateBackUseCase,
    private val getMenuActionsUseCase: GetMenuActionsUseCase,
    private val navigateToRenameUseCase: NavigateToRenameUseCase,
    private val navigateToNewDirectoryUseCase: NavigateToNewDirectoryUseCase,
    private val navigateToPathUseCase: NavigateToPathUseCase,
    private val openFileUseCase: OpenFileUseCase,
    private val isDirectoryUseCase: IsDirectoryUseCase,
    private val navigateToDeleteUseCase: NavigateToDeleteUseCase,
    private val pinUseCase: PinUseCase,
    private val unpinUseCase: UnpinUseCase
) : ViewModel() {

    var state by mutableStateOf<UiState>(UiState.Loading)
        private set

    fun onEvent(event: Event) {
        when (event) {
            is Event.OnNavigateBack -> {
                navigateBackUseCase()
            }

            is Event.OnHeaderClick -> {
                val isDirectory = isDirectoryUseCase(path = event.path)
                if (isDirectory) {
                    onEvent(
                        OnDirectoryClick(
                            path = event.path
                        )
                    )
                } else {
                    onEvent(
                        OnFileClick(
                            path = event.path
                        )
                    )
                }
            }

            is Event.OnNavigateToRename -> {
                navigateToRenameUseCase(event.path)
            }

            is Event.OnNavigateToDelete -> {
                navigateToDeleteUseCase(event.path)
            }

            is Event.OnNavigateToNewDirectory -> {
                navigateToNewDirectoryUseCase(event.path)
            }

            is OnDirectoryClick -> {
                navigateToPathUseCase(event.path)
            }

            is OnFileClick -> {
                openFileUseCase(event.path)
            }

            is Event.OnGetMenuActions -> {
                viewModelScope.launch {
                    val mode = event.mode
                    val actions = getMenuActionsUseCase(event.path, mode)
                    state = Success(
                        name = event.path.split("/").last(), actions = actions
                    )
                }
            }

            is Event.OnPin -> {
                viewModelScope.launch {
                    pinUseCase(event.path)
                }
            }

            is Event.OnUnpin -> {
                viewModelScope.launch {
                    unpinUseCase(event.path)
                }
            }
        }
    }


}
