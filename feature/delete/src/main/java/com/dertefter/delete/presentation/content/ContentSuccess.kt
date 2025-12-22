package com.dertefter.delete.presentation.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dertefter.delete.R
import com.dertefter.delete.presentation.Event
import com.dertefter.design.components.basic_screens.DialogDefaultScreen
import com.dertefter.design.icons.Icons
import com.dertefter.design.theme.TheTheme

@Composable
fun ContentSuccess(
    path: String,
    onEvent: (Event) -> Unit,
) {

    DialogDefaultScreen(
        title = stringResource(R.string.delete_title),
        onCancel = {
            onEvent(Event.OnNavigateBack)
        },
        onOk = {
            onEvent(Event.OnDelete(path))
        },
        okIcon = Icons.Delete,
        cancelIcon = Icons.ArrowBack
    )

}

@Composable
@Preview(device = "id:wearos_small_round", showSystemUi = false, showBackground = true)
fun ContentFailedPreview(){
    TheTheme() {
        ContentSuccess(
            "33333",{}
        )
    }
}