package com.dertefter.delete.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dertefter.delete.presentation.content.ContentFailed
import com.dertefter.delete.presentation.content.ContentSuccess


@Composable
fun DeleteScreen(
    uiState: UiState,
    onEvent: (Event) -> Unit,
) {


    when(uiState){
        is UiState.Failed -> {
            ContentFailed(uiState.e, onEvent)
        }
        is UiState.Loading -> {
        }
        is UiState.Success -> {
            ContentSuccess(
                paths = uiState.paths,
                onEvent = onEvent,
            )
        }

    }

}

@Composable
@Preview(device = "id:wearos_small_round", showBackground = true, showSystemUi = false)
fun DeleteScreenPreview(){
    DeleteScreen(uiState = UiState.Loading, {})
}