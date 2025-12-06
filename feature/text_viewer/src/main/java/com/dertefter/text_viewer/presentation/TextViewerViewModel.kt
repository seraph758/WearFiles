package com.dertefter.text_viewer.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dertefter.text_viewer.presentation.content.UiState
import com.dertefter.text_viewer.usecase.GetFileTextContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class TextViewerViewModel @Inject constructor(
    private val getFileTextContentUseCase: GetFileTextContentUseCase,
) : ViewModel() {

    var state by mutableStateOf<UiState>(UiState.Loading)
        private set

    fun onEvent(event: Event) {
        when (event) {
            is Event.OnGetFileTextContent -> {
                getFileTextContent(event.uriString)
            }

        }
    }

    private fun getFileTextContent(path: String) {
        viewModelScope.launch {
            state = UiState.Loading
            try {
                val content = getFileTextContentUseCase(path)
                state = UiState.Success(content)
            } catch (e: Exception) {
                state = UiState.Failed
            }
        }
    }

}
