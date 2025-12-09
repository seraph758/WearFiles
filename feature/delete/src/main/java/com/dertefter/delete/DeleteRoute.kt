package com.dertefter.delete

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.dertefter.delete.presentation.DeleteScreen
import com.dertefter.delete.presentation.DeleteViewModel

@Composable
fun DeleteRoute(
    path: String,
    viewModel: DeleteViewModel = hiltViewModel()
) {

    val uiState = viewModel.state

    LaunchedEffect(Unit) {
        viewModel.setPath(path)
    }


    DeleteScreen(
        uiState = uiState,
        onEvent = viewModel::onEvent,
    )

}
