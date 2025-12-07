package com.dertefter.file_list.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dertefter.data.model.PrettyPath
import com.dertefter.file_list.presentation.content.UiState
import com.dertefter.file_list.usecase.CheckIfNavigateUpAvaibleUseCase
import com.dertefter.file_list.usecase.GetBasePathUseCase
import com.dertefter.file_list.usecase.GetFileListUseCase
import com.dertefter.file_list.usecase.GetParentUseCase
import com.dertefter.file_list.usecase.NavigateToPathUseCase
import com.dertefter.file_list.usecase.NavigateBackUseCase
import com.dertefter.file_list.usecase.OpenFileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FileListViewModel @Inject constructor(
    private val navigateToPathUseCase: NavigateToPathUseCase,
    private val getFileListUseCase: GetFileListUseCase,
    private val getBasePathUseCase: GetBasePathUseCase,
    private val getParentUseCase: GetParentUseCase,
    private val openFileUseCase: OpenFileUseCase,
    private val navigateBackUseCase: NavigateBackUseCase,
    private val checkIfNavigateUpAvaibleUseCase: CheckIfNavigateUpAvaibleUseCase
) : ViewModel() {

    var state by mutableStateOf<UiState>(UiState.Loading)
        private set

    fun onEvent(event: Event) {
        when (event) {
            is Event.OnGetFileListAtPath -> {
                getFileListAtPath(event.path ?: getBasePathUseCase())
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
        }
    }


    private fun navigateToParent(path: String) {
        viewModelScope.launch {
            val parentPath = getParentUseCase(path)
            if (parentPath != null){
                navigateToPathUseCase(parentPath)
            }
        }
    }

    private fun getFileListAtPath(path: String) {
        viewModelScope.launch {
            state = UiState.Loading
            try {

                val canNavigateUp = checkIfNavigateUpAvaibleUseCase(path)

                val fileList = getFileListUseCase(path)

                val actions = mutableListOf<Action>()

                val homePath = getBasePathUseCase()

                if (canNavigateUp){
                    actions.add(Action.MOVE_UP)
                    actions.add(Action.MOVE_BACK)
                }

                state = UiState.Success(
                    path = PrettyPath(
                        homePath,
                        path
                    ),
                    files = fileList,
                    actions = actions
                )

            } catch (e: Exception) {
                state = UiState.Failed
            }
        }
    }


}
