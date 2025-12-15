package com.dertefter.text_viewer.presentation.content

import androidx.compose.runtime.Composable
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import com.dertefter.design.components.basic_screens.ContentFailedDefaultScreen
import com.dertefter.text_viewer.presentation.Event

@Composable
fun ContentFailed(
    e: Throwable? = null,
    onEvent: (Event) -> Unit,
){
    ContentFailedDefaultScreen(
        e = e,
        title = "2",
        onBackClick = {}
    )
}

@Composable
@WearPreviewDevices
fun ContentErrorPreview(){
    ContentFailed(null, {})
}