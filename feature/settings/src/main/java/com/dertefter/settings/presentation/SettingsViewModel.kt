package com.dertefter.settings.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dertefter.navigation.Routes
import com.dertefter.settings.usecase.NavigateToUseCase
import com.dertefter.settings.usecase.OpenLinkOnPhoneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val navigateToUseCase: NavigateToUseCase,
    private val openLinkOnPhoneUseCase: OpenLinkOnPhoneUseCase
) : ViewModel() {

    var dialogState by mutableStateOf(DialogState.CLOSED)
        private set

    fun onEvent(event: Event) {
        when (event) {

            is Event.OnNavigateBack -> {

            }

            Event.OnNavigateToGitRepo -> {
                openLinkOnPhone()
            }

            Event.OnNavigateToTheming -> {
                navigateToUseCase(routes = Routes.Theming)
            }

            is Event.CloseDialog -> {
                dialogState = DialogState.CLOSED
            }

            is Event.ShowDialog -> {
                dialogState = if (event.isSuccessful) {
                    DialogState.SUCCESS
                } else {
                    DialogState.FAILED
                }
            }
        }
    }

    private fun openLinkOnPhone() {
        viewModelScope.launch {
            openLinkOnPhoneUseCase("https://github.com/dertefter/WearFiles").onSuccess {
                    onEvent(Event.ShowDialog(isSuccessful = true))
                }.onFailure {
                    onEvent(Event.ShowDialog(isSuccessful = false))
                }

        }

    }

}
