package com.dertefter.onboarding.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dertefter.onboarding.presentation.content.OnBoardingState
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


    var state by mutableStateOf(OnBoardingState.LOADING)
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
        }
    }


    private fun openLinkOnPhone(){
        viewModelScope.launch {
            openLinkOnPhoneUseCase("https://google.com")
        }

    }

    private fun navigateToFileList() {
        navigateToFileListUseCase()
    }

    private fun checkPermissions(){
        state = OnBoardingState.LOADING

        val hasFileAccess = checkFileAccessUseCase()

        state = if (hasFileAccess){
            viewModelScope.launch {
                onEvent(Event.OnNavigateToFileList)
            }
            OnBoardingState.SUCCESS
        } else {
            OnBoardingState.FAILED
        }

    }

}
