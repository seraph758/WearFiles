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
import com.dertefter.menu.usecase.CopyUseCase
import com.dertefter.menu.usecase.CutUseCase
import com.dertefter.menu.usecase.GetMenuActionsUseCase
import com.dertefter.menu.usecase.IsDirectoryUseCase
import com.dertefter.menu.usecase.NavigateToDeleteUseCase
import com.dertefter.menu.usecase.NavigateToNewDirectoryUseCase
import com.dertefter.menu.usecase.NavigateToPathUseCase
import com.dertefter.menu.usecase.NavigateToRenameUseCase
import com.dertefter.menu.usecase.OpenFileUseCase
import com.dertefter.menu.usecase.PasteCancelUseCase
import com.dertefter.menu.usecase.PasteHereUseCase
import com.dertefter.menu.usecase.PinUseCase
import com.dertefter.menu.usecase.UnpinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getMenuActionsUseCase: GetMenuActionsUseCase,
    private val navigateToRenameUseCase: NavigateToRenameUseCase,
    private val navigateToNewDirectoryUseCase: NavigateToNewDirectoryUseCase,
    private val navigateToPathUseCase: NavigateToPathUseCase,
    private val openFileUseCase: OpenFileUseCase,
    private val isDirectoryUseCase: IsDirectoryUseCase,
    private val navigateToDeleteUseCase: NavigateToDeleteUseCase,
    private val pinUseCase: PinUseCase,
    private val unpinUseCase: UnpinUseCase,
    private val cutUseCase: CutUseCase,
    private val copyUseCase: CopyUseCase,
    private val pasteHereUseCase: PasteHereUseCase,
    private val cancelPasteUseCase: PasteCancelUseCase

) : ViewModel() {

    var state by mutableStateOf<UiState>(UiState.Loading)
        private set

    private var navigateBackUseCase: () -> Unit = {  }

    fun setOnDismiss(onDismissRequest: () -> Unit) {
        navigateBackUseCase = onDismissRequest
    }

    fun onEvent(event: Event) {
        when (event) {
            is Event.OnNavigateBack -> {
                navigateBackUseCase()
            }

            is Event.OnHeaderClick -> {
                viewModelScope.launch {
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
                    navigateBackUseCase()
                }
            }

            is Event.OnNavigateToRename -> {
                navigateToRenameUseCase(event.path)
                navigateBackUseCase()
            }

            is Event.OnNavigateToDelete -> {
                navigateToDeleteUseCase(event.paths)
                navigateBackUseCase()
            }

            is Event.OnNavigateToNewDirectory -> {
                navigateToNewDirectoryUseCase(event.path)
                navigateBackUseCase()
            }

            is OnDirectoryClick -> {
                navigateToPathUseCase(event.path)
                navigateBackUseCase()
            }

            is OnFileClick -> {
                openFileUseCase(event.path)
                navigateBackUseCase()
            }

            is Event.OnGetMenuActions -> {
                viewModelScope.launch {
                    val mode = event.mode
                    val actions = getMenuActionsUseCase(event.paths, mode)
                    state = Success(
                        name = if (event.paths.size == 1) event.paths.first().split("/").last() else "", actions = actions
                    )
                }
            }

            is Event.OnPin -> {
                viewModelScope.launch {
                    pinUseCase(event.paths)
                    navigateBackUseCase()
                }
            }

            is Event.OnUnpin -> {
                viewModelScope.launch {
                    unpinUseCase(event.paths)
                    navigateBackUseCase()
                }
            }

            is Event.OnCancelPaste -> {
                cancelPasteUseCase()
                navigateBackUseCase()
            }

            is Event.OnCopy -> {
                copyUseCase(event.paths)
                navigateBackUseCase()
            }
            is Event.OnCut -> {
                cutUseCase(event.paths)
                navigateBackUseCase()
            }
            is Event.OnPaste -> {
                viewModelScope.launch {
                    state = UiState.Operation
                    pasteHereUseCase(event.path)
                    navigateBackUseCase()
                }

            }
        }
    }


}
