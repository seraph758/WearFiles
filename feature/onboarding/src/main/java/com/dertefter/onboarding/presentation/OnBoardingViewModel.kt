package com.dertefter.onboarding.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dertefter.onboarding.presentation.content.DialogState
import com.dertefter.onboarding.presentation.content.UiState
import com.dertefter.onboarding.usecase.CheckFileAccessUseCase
import com.dertefter.onboarding.usecase.NavigateToFileListUseCase
import com.dertefter.onboarding.usecase.OpenLinkOnPhoneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    val checkFileAccessUseCase: CheckFileAccessUseCase,
    val openLinkOnPhoneUseCase: OpenLinkOnPhoneUseCase,
    val navigateToFileListUseCase: NavigateToFileListUseCase
) : ViewModel() {


    var state by mutableStateOf(UiState.LOADING)
        private set

    var dialogState by mutableStateOf(DialogState.CLOSED)
        private set


    fun onEvent(event: Event) {
        when (event) {
            is Event.OnOpenLinkOnPhone -> {
                openLinkOnPhone()
            }

            is Event.OnCheckPermissions -> {
                checkPermissions()
            }

            is Event.OnNavigateToFileList -> {
                navigateToFileList()
            }

            is Event.CloseDialog -> {
                dialogState = DialogState.CLOSED
            }

            is Event.ShowDialog -> {
                dialogState = if (event.isSuccessful){
                    DialogState.SUCCESS
                } else {
                    DialogState.FAILED
                }
            }
        }
    }


    private fun openLinkOnPhone(){
        viewModelScope.launch {
            openLinkOnPhoneUseCase("https://google.com")
                .onSuccess {
                    onEvent(Event.ShowDialog(isSuccessful = true))
                }
                .onFailure {
                    onEvent(Event.ShowDialog(isSuccessful = false))
                }

        }

    }

    private fun navigateToFileList() {
        navigateToFileListUseCase()
    }

    private fun checkPermissions(){
        state = UiState.LOADING

        val hasFileAccess = checkFileAccessUseCase()

        state = if (hasFileAccess){
            viewModelScope.launch {
                onEvent(Event.OnNavigateToFileList)
            }
            UiState.SUCCESS
        } else {
            UiState.FAILED
        }

    }

}
