package com.dertefter.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor() : ViewModel() {

    private val _sideEffects = MutableSharedFlow<OnBoardingSideEffect>()
    val sideEffects = _sideEffects.asSharedFlow()

    fun onInfoClick() {
        viewModelScope.launch {
            _sideEffects.emit(OnBoardingSideEffect.OpenUrl("https://github.com/DerTefter/WearFiles"))
        }
    }
}

sealed class OnBoardingSideEffect {
    data class OpenUrl(val url: String) : OnBoardingSideEffect()
}
