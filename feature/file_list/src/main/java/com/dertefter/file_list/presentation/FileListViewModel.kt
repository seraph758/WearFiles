package com.dertefter.file_list.presentation

import android.os.Environment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dertefter.data.model.PrettyPath
import com.dertefter.file_list.presentation.content.MenuState
import com.dertefter.file_list.presentation.content.UiState
import com.dertefter.file_list.usecase.CheckFileAccessUseCase
import com.dertefter.file_list.usecase.GetActionsUseCase
import com.dertefter.file_list.usecase.GetBasePathUseCase
import com.dertefter.file_list.usecase.GetFileListUseCase
import com.dertefter.file_list.usecase.GetParentUseCase
import com.dertefter.file_list.usecase.NavigateBackUseCase
import com.dertefter.file_list.usecase.NavigateToPathUseCase
import com.dertefter.file_list.usecase.OpenFileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class FileListViewModel @Inject constructor(
    private val navigateToPathUseCase: NavigateToPathUseCase,
    private val getFileListUseCase: GetFileListUseCase,
    private val getBasePathUseCase: GetBasePathUseCase,
    private val getParentUseCase: GetParentUseCase,
    private val openFileUseCase: OpenFileUseCase,
    private val navigateBackUseCase: NavigateBackUseCase,
    private val getActionsUseCase: GetActionsUseCase,
    private val checkFileAccessUseCase: CheckFileAccessUseCase
) : ViewModel() {

    var state by mutableStateOf<UiState>(UiState.Loading)
        private set

    var menuState by mutableStateOf<MenuState>(MenuState.Hide)
        private set

    fun onEvent(event: Event) {
        when (event) {
            is Event.OnGetFileListAtPath -> {

                val hasFileAccess = checkFileAccessUseCase()
                val path = event.path ?: getBasePathUseCase()
                val isReceivedFolder = path.contains("Download/Received", ignoreCase = true)

                if (!hasFileAccess && !isReceivedFolder) {
                    state = UiState.NoPermissions
                } else {
                    getFileListAtPath(path)
                }
            }

            is Event.OnFileClick -> {
                openFileUseCase(event.file)
            }

            is Event.OnDirectoryClick -> {
                navigateToPathUseCase(event.path)
            }

            is Event.OnNavigateBack -> {
                navigateBackUseCase()
            }

            is Event.OnNavigateToParent -> {
                navigateToParent(event.path)
            }

            is Event.OnHideMenu -> {
                menuState = MenuState.Hide
            }

            is Event.OnShowMenuFor -> {
                menuState = MenuState.Show(event.path, event.menuMode)
            }

        }
    }


    private fun navigateToParent(path: String) {
        viewModelScope.launch {
            val parentPath = getParentUseCase(path)
            if (parentPath != null) {
                navigateToPathUseCase(parentPath)
            }
        }
    }

    private fun getFileListAtPath(path: String) {
        viewModelScope.launch {
            val currentState = state
            if (currentState is UiState.Success && currentState.path.path == path) {
                // Keep the current state to avoid UI flicker and scroll reset
            } else {
                state = UiState.Loading
            }

            getFileListUseCase(path).onSuccess { fileList ->

                    val actions = getActionsUseCase(path)

                    var homePath = getBasePathUseCase()

                    val downloadsPath =
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath
                    val receivedPath = File(downloadsPath, "Received").absolutePath

                    if (path.startsWith(receivedPath)) {
                        homePath = receivedPath
                    }

                    state = UiState.Success(
                        path = PrettyPath(
                            homePath, path
                        ), files = fileList, actions = actions
                    )

                }.onFailure { e ->
                    state = UiState.Failed(e)
                }


        }
    }


}
