package com.dertefter.image_viewer.presentation.content

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@Composable
fun ContentSuccess(
    uriString: String
) {
    val context = LocalContext.current

    val zoomState = rememberZoomState()

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(uriString)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .fillMaxSize()
            .zoomable(zoomState)
    )
}

@Composable
@Preview
fun ContentSuccessPreview() {
    ContentSuccess("")
}
