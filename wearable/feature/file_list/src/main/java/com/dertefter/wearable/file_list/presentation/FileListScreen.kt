package com.dertefter.wearable.file_list.presentation

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.dialog.Dialog
import com.dertefter.wearable.design.components.basic_screens.ContentLoadingDefaultScreen
import com.dertefter.wearable.file_list.presentation.content.ContentSuccess
import com.dertefter.wearable.file_list.presentation.content.MenuState
import com.dertefter.wearable.file_list.presentation.content.UiState
import com.dertefter.wearable.menu.MenuRoute
import com.dertefter.wearable.onboarding.OnBoardingRoute

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
                paths = menuState.paths,
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
