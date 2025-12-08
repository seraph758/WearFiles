package com.dertefter.file_list.presentation.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dertefter.design.components.basic_screens.ContentFailedDefaultScreen
import com.dertefter.file_list.R
import com.dertefter.menu.presentation.Event

@Composable
fun ContentFailed(
    e: Throwable? = null,
    onEvent: (Event) -> Unit,
){
    ContentFailedDefaultScreen(
        e = e,
        title = stringResource(id = R.string.file_list_error),
        onBackClick = {onEvent(Event.OnNavigateBack)}
    )
}

@Composable
@Preview(device = "id:wearos_large_round", showBackground = true, showSystemUi = false)
fun ContentErrorPreview(){
    ContentFailed(null, {})
}