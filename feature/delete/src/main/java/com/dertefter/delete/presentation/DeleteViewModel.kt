package com.dertefter.delete.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dertefter.delete.usecase.DeleteUseCase
import com.dertefter.delete.usecase.NavigateBackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteViewModel @Inject constructor(
    private val deleteUseCase: DeleteUseCase,
    private val navigateBackUseCase: NavigateBackUseCase
) : ViewModel() {


    var state by mutableStateOf<UiState>(UiState.Loading)
        private set

    fun setPath(path: String){
        state = UiState.Success(path)
    }

    fun onEvent(event: Event) {
        when (event) {

            is Event.OnDelete -> {
                viewModelScope.launch {
                    deleteUseCase(event.path)
                        .onSuccess { navigateBackUseCase() }
                        .onFailure { state = UiState.Failed(it) }
                }
            }

            is Event.OnNavigateBack -> {
                navigateBackUseCase()
            }
        }
    }


}
