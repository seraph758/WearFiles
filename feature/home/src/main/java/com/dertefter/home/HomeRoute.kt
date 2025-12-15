package com.dertefter.home

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.dertefter.home.presentation.HomeScreen
import com.dertefter.home.presentation.HomeViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val uiState = viewModel.state

    HomeScreen(
        uiState = uiState,
        onEvent = { event ->
            viewModel.onEvent(event)
        },
    )

}
