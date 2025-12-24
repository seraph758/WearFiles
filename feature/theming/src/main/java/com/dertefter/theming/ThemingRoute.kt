package com.dertefter.theming

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.dertefter.theming.presentation.ThemingScreen
import com.dertefter.theming.presentation.ThemingViewModel

@Composable
fun ThemingRoute(
    viewModel: ThemingViewModel = hiltViewModel()
) {

    val uiState = viewModel.state

    ThemingScreen(
        uiState = uiState,
        onEvent = viewModel::onEvent,
    )

}
