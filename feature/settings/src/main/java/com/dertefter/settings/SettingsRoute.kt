package com.dertefter.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.dertefter.settings.presentation.Event
import com.dertefter.settings.presentation.SettingsViewModel
import com.dertefter.settings.presentation.SettingsScreen

@Composable
fun SettingsRoute(
    viewModel: SettingsViewModel = hiltViewModel(),
) {

    val dialogState = viewModel.dialogState

    SettingsScreen(
        onEvent = { event ->
            viewModel.onEvent(event)
        },
        dialogState = dialogState
    )

}
