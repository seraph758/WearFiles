package com.dertefter.wearable.home

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.dertefter.wearable.home.presentation.HomeScreen
import com.dertefter.wearable.home.presentation.HomeViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val uiState = viewModel.state

    val menuState = viewModel.menuState

    HomeScreen(
        uiState = uiState,
        menuState = menuState,
        onEvent = { event ->
            viewModel.onEvent(event)
        },
    )

}
