package com.dertefter.rename.presentation.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import com.dertefter.design.components.basic_screens.ContentFailedDefaultScreen
import com.dertefter.rename.R
import com.dertefter.rename.presentation.Event

@Composable
fun ContentFailed(
    e: Throwable? = null,
    onEvent: (Event) -> Unit,
){
    ContentFailedDefaultScreen(
        e = e,
        title = stringResource(id = R.string.rename_failed),
        onBackClick = {onEvent(Event.OnNavigateBack)}
    )
}

@Composable
@WearPreviewDevices
fun ContentErrorPreview(){
    ContentFailed(null, {})
}