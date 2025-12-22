package com.dertefter.music.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dertefter.music.presentation.content.PermissionDialogState
import com.dertefter.music.presentation.content.UiState
import com.dertefter.music.usecase.CheckPermissionsUseCase
import com.dertefter.music.usecase.GetMusicUseCase
import com.dertefter.music.usecase.OpenFileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val getMusicUseCase: GetMusicUseCase,
    private val checkPermissionsUseCase: CheckPermissionsUseCase,
    private val openFileUseCase: OpenFileUseCase
) : ViewModel() {

    var state by mutableStateOf<UiState>(UiState.Loading)
        private set

    var permissionDialogState by mutableStateOf(PermissionDialogState.HIDE)
        private set


    fun onEvent(event: Event) {
        when (event) {

            is Event.OnNavigateBack -> {

            }
            is Event.OnMediaClick -> {
                openFileUseCase(event.item.uri)
            }
            is Event.OnLoad -> {
                val hasPermissions = checkPermissionsUseCase()

                if (hasPermissions){
                    permissionDialogState = PermissionDialogState.HIDE
                    loadMedia()
                } else {
                    permissionDialogState = PermissionDialogState.SHOW
                }

            }
        }
    }

    private fun loadMedia() {
        viewModelScope.launch {
            state = UiState.Loading
            getMusicUseCase()
                .onSuccess { list ->
                    state = UiState.Success(musicItems = list)
                }
                .onFailure { e ->
                    state = UiState.Failed(e)
                }
        }
    }

}
