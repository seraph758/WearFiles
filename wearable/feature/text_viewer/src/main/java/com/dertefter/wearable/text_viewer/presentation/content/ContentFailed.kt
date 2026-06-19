package com.dertefter.wearable.text_viewer.presentation.content

import androidx.compose.runtime.Composable
import com.dertefter.wearable.design.components.basic_screens.ContentFailedDefaultScreen

@Composable
fun ContentFailed() {
    ContentFailedDefaultScreen(
        title = "Error",
        onBackClick = {}
    )
}