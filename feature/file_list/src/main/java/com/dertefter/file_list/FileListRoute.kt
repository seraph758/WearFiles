package com.dertefter.file_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.dertefter.file_list.presentation.Event
import com.dertefter.file_list.presentation.FileListScreen
import com.dertefter.file_list.presentation.FileListViewModel

@Composable
fun FileListRoute(
    viewModel: FileListViewModel = hiltViewModel(),
    path: String? = null
) {

    val uiState = viewModel.state

    LaunchedEffect(path) {
        viewModel.onEvent(Event.OnGetFileListAtPath(path))
    }

    FileListScreen(
        uiState = uiState,
        menuState = viewModel.menuState,
        onEvent = { event ->
            viewModel.onEvent(event)
        },
    )

}
