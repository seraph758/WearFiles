package com.dertefter.home.presentation

import androidx.compose.runtime.Composable
import com.dertefter.design.components.basic_screens.ContentLoadingDefaultScreen

@Composable
fun HomeScreen(onEvent: (Event) -> Unit, uiState: UiState) {



    when(uiState){
        is UiState.Loading -> ContentLoadingDefaultScreen()
        is UiState.Success -> ContentSuccess(uiState.pinned)
        is UiState.Failed -> {}
     }

}
