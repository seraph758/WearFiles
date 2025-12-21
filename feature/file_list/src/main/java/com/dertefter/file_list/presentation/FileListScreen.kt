package com.dertefter.file_list.presentation

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.wear.compose.material.dialog.Dialog
import com.dertefter.design.components.basic_screens.ContentLoadingDefaultScreen
import com.dertefter.file_list.presentation.content.ContentSuccess
import com.dertefter.file_list.presentation.content.MenuState
import com.dertefter.file_list.presentation.content.UiState
import com.dertefter.menu.MenuRoute
import com.dertefter.onboarding.OnBoardingRoute

@Composable
fun FileListScreen(onEvent: (Event) -> Unit, uiState: UiState, menuState: MenuState) {


    Dialog(
        showDialog = menuState is MenuState.Show,
        onDismissRequest = {
            onEvent(Event.OnHideMenu)
        }
    ) {
        if (menuState is MenuState.Show){
            MenuRoute(
                path = menuState.path,
                onDismissRequest = {
                onEvent(Event.OnHideMenu)
                },
                mode = menuState.menuMode
            )
        }
    }


    when(uiState){
        is UiState.Loading -> ContentLoadingDefaultScreen()
        is UiState.Success -> ContentSuccess(
            uiState.path,
            uiState.files,
            onEvent,
            uiState.actions
            )
        is UiState.Failed -> {
            onEvent(Event.OnNavigateBack)
        }

        is UiState.NoPermissions -> {
            OnBoardingRoute()
        }
     }

}
