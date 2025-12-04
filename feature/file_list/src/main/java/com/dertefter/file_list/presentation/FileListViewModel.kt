package com.dertefter.file_list.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dertefter.file_list.presentation.content.UiState
import com.dertefter.file_list.usecase.GetBasePathUseCase
import com.dertefter.file_list.usecase.GetFileListUseCase
import com.dertefter.file_list.usecase.NavigateToPathUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FileListViewModel @Inject constructor(
    private val navigateToPathUseCase: NavigateToPathUseCase,
    private val getFileListUseCase: GetFileListUseCase,
    private val getBasePathUseCase: GetBasePathUseCase
) : ViewModel() {


    var state by mutableStateOf<UiState>(UiState.Loading)
        private set

    fun onEvent(event: Event) {
        when (event) {
            is Event.OnGetFileListAtPath -> {
                getFileListAtPath(event.path ?: getBasePathUseCase())
            }
            is Event.OnFileClick -> {

            }

            is Event.OnDirectoryClick -> {
                navigateToPathUseCase(event.file.path)
            }
        }
    }

    private fun getFileListAtPath(path: String) {
        viewModelScope.launch {
            state = UiState.Loading
            try {
                val fileList = getFileListUseCase(path)
                state = UiState.Success(fileList)
            } catch (e: Exception) {
                state = UiState.Failed
            }
        }
    }

}
