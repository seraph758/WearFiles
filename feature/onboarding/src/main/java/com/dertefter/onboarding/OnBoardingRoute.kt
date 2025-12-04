package com.dertefter.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.dertefter.onboarding.presentation.Event
import com.dertefter.onboarding.presentation.OnBoardingScreen
import com.dertefter.onboarding.presentation.OnBoardingViewModel

@Composable
fun OnBoardingRoute(
    viewModel: OnBoardingViewModel = hiltViewModel()
) {

    val uiState = viewModel.state

    LaunchedEffect(Unit) {
        viewModel.onEvent(Event.OnCheckPermissions)
    }

    OnBoardingScreen(
        uiState = uiState,
        onEvent = viewModel::onEvent,
    )

}