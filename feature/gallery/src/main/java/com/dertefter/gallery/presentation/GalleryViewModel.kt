package com.dertefter.gallery.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dertefter.gallery.presentation.content.PermissionDialogState
import com.dertefter.gallery.presentation.content.UiState
import com.dertefter.gallery.usecase.CheckPermissionsUseCase
import com.dertefter.gallery.usecase.GetMediaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getMediaUseCase: GetMediaUseCase,
    private val checkPermissionsUseCase: CheckPermissionsUseCase,
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
            getMediaUseCase()
                .onSuccess { list ->
                    state = UiState.Success(mediaItems = list)
                }
                .onFailure { e ->
                    state = UiState.Failed(e)
                }
        }
    }

}
