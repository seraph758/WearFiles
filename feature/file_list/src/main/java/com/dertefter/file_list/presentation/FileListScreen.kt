package com.dertefter.file_list.presentation

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.dialog.Dialog
import com.dertefter.design.components.basic_screens.ContentLoadingDefaultScreen
import com.dertefter.file_list.presentation.content.ContentSuccess
import com.dertefter.file_list.presentation.content.MenuState
import com.dertefter.file_list.presentation.content.UiState
import com.dertefter.menu.MenuRoute

@Composable
fun FileListScreen(onEvent: (Event) -> Unit, uiState: UiState, menuState: MenuState) {

    Dialog(
        showDialog = menuState is MenuState.Show,
        onDismissRequest = {
            onEvent(Event.OnHideMenu)
        }
    ) {
        if (menuState is MenuState.Show){
            MenuRoute(path = menuState.path, onDismissRequest = {
                onEvent(Event.OnHideMenu)
            })
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
     }

}
