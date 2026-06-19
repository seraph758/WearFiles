package com.dertefter.wearable.settings

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.dertefter.wearable.settings.presentation.SettingsScreen
import com.dertefter.wearable.settings.presentation.SettingsViewModel

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
