package com.dertefter.delete.presentation.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dertefter.delete.R
import com.dertefter.delete.presentation.Event
import com.dertefter.design.components.basic_screens.ContentFailedDefaultScreen

@Composable
fun ContentFailed(
    e: Throwable? = null,
    onEvent: (Event) -> Unit,
){
    ContentFailedDefaultScreen(
        e = e,
        title = stringResource(id = R.string.delete_failed),
        onBackClick = {onEvent(Event.OnNavigateBack)}
    )
}

@Composable
@Preview(device = "id:wearos_large_round", showBackground = true, showSystemUi = false)
fun ContentErrorPreview(){
    ContentFailed(null, {})
}