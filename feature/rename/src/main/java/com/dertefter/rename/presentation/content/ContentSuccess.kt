package com.dertefter.rename.presentation.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dertefter.design.components.basic_screens.TextFieldDefaultScreen
import com.dertefter.design.theme.TheTheme
import com.dertefter.rename.R
import com.dertefter.rename.presentation.Event

@Composable
fun ContentSuccess(
    path: String,
    newFileName: String,
    isSaveEnabled: Boolean,
    onEvent: (Event) -> Unit,
) {

    TextFieldDefaultScreen(
        isSaveEnabled = isSaveEnabled,
        onSaveClick = {
            onEvent(
                Event.OnRename(
                    path = path,
                    newName = newFileName
                )
            )
        },
        onValueChange = { onEvent(Event.OnNewFileNameChanged(it)) },
        value = newFileName,
        label = stringResource(R.string.rename_name)
    )

}

@Composable
@Preview(device = "id:wearos_small_round", showSystemUi = false, showBackground = true)
fun ContentFailedPreview(){
    TheTheme() {
        ContentSuccess(
            "33333","22222",  true, {}
        )
    }
}