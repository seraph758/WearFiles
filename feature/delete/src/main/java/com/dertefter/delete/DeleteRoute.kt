package com.dertefter.delete

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.dertefter.delete.presentation.DeleteScreen
import com.dertefter.delete.presentation.DeleteViewModel

@Composable
fun DeleteRoute(
    paths: List<String>,
    viewModel: DeleteViewModel = hiltViewModel()
) {

    val uiState = viewModel.state

    LaunchedEffect(Unit) {
        viewModel.setPaths(paths)
    }


    DeleteScreen(
        uiState = uiState,
        onEvent = viewModel::onEvent,
    )

}
