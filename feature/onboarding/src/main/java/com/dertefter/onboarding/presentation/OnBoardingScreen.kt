package com.dertefter.onboarding.presentation

import androidx.compose.runtime.Composable
import com.dertefter.onboarding.presentation.content.ContentFailed
import com.dertefter.onboarding.presentation.content.ContentLoading
import com.dertefter.onboarding.presentation.content.ContentSuccess
import com.dertefter.onboarding.presentation.content.OnBoardingState

@Composable
fun OnBoardingScreen(onEvent: (Event) -> Unit, uiState: OnBoardingState) {

    when(uiState){
        OnBoardingState.LOADING -> ContentLoading()
        OnBoardingState.SUCCESS -> ContentSuccess()
        OnBoardingState.FAILED -> ContentFailed(onEvent = onEvent)
    }

}
