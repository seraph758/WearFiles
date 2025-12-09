package com.dertefter.menu.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dertefter.menu.presentation.content.UiState
import com.dertefter.menu.usecase.GetMenuActionsUseCase
import com.dertefter.menu.usecase.IsDirectoryUseCase
import com.dertefter.menu.usecase.NavigateBackUseCase
import com.dertefter.menu.usecase.NavigateToDeleteUseCase
import com.dertefter.menu.usecase.NavigateToNewDirectoryUseCase
import com.dertefter.menu.usecase.NavigateToPathUseCase
import com.dertefter.menu.usecase.NavigateToRenameUseCase
import com.dertefter.menu.usecase.OpenFileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val navigateBackUseCase: NavigateBackUseCase,
    private val getMenuActionsUseCase: GetMenuActionsUseCase,
    private  val navigateToRenameUseCase: NavigateToRenameUseCase,
    private  val navigateToNewDirectoryUseCase: NavigateToNewDirectoryUseCase,
    private val navigateToPathUseCase: NavigateToPathUseCase,
    private val openFileUseCase: OpenFileUseCase,
    private val isDirectoryUseCase: IsDirectoryUseCase,
    private val navigateToDeleteUseCase: NavigateToDeleteUseCase
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
                        Event.OnDirectoryClick(
                            path = event.path
                        )
                    )
                } else {
                    onEvent(
                        Event.OnFileClick(
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

            is Event.OnDirectoryClick -> {
                navigateToPathUseCase(event.path)
            }

            is Event.OnFileClick -> {
                openFileUseCase(event.path)
            }

            is Event.OnGetMenuActions -> {
                val mode = event.mode
                val actions = getMenuActionsUseCase(event.path, mode)
                state = UiState.Success(
                    name = event.path.split("/").last(),
                    actions = actions
                )
            }

        }
    }


}
