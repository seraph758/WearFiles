package com.dertefter.rename.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dertefter.rename.usecase.GetFileNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RenameViewModel @Inject constructor(
    private val getFileNameUseCase: GetFileNameUseCase
) : ViewModel() {


    var state by mutableStateOf<UiState>(UiState.Loading)
        private set

    fun onEvent(event: Event) {
        when (event) {
           is   Event.OnGetFileName -> {
               getFileNameUseCase(event.path)
                   .onSuccess { name ->
                       state = UiState.Success(
                           path = event.path,
                           fileName = name,
                           newFileName = name
                       )
                   }
                   .onFailure {
                       state = UiState.Failed(it)
                   }
           }
            is Event.OnNewFileNameChanged -> {
                val currentState = state
                if (currentState is UiState.Success) {
                    state = currentState.copy(newFileName = event.name)
                }
            }
        }
    }


}
