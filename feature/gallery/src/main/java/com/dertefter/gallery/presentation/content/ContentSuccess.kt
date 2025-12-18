package com.dertefter.gallery.presentation.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.ListHeader
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.SurfaceTransformation
import androidx.wear.compose.material3.lazy.rememberTransformationSpec
import androidx.wear.compose.material3.lazy.transformedHeight
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dertefter.gallery.data.MediaItem

@Composable
fun ContentSuccess(
    media: List<MediaItem>,
    onClick: (MediaItem) -> Unit = {}
) {

    val columnState = rememberTransformingLazyColumnState()

    val transformationSpec = rememberTransformationSpec()

    ScreenScaffold(
        scrollState = columnState,
        contentPadding = PaddingValues(
            top = 52.dp, start = 10.dp, end = 10.dp, bottom = 52.dp
        ),
    )
    { contentPadding ->

        val context = LocalContext.current

        TransformingLazyColumn(
            state = columnState,
            contentPadding = contentPadding,
        ) {

            media.chunked(3).forEach { rowItems ->
                item {
                    ListHeader(
                        modifier = Modifier
                            .fillMaxWidth()
                            .transformedHeight(this, transformationSpec),
                        transformation = SurfaceTransformation(transformationSpec)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            rowItems.forEach { item ->
                                AsyncImage(
                                    model = ImageRequest.Builder(context)
                                        .data(item.uri)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = item.displayName,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(1f)
                                        .clip(RoundedCornerShape(50))
                                )
                            }
                            repeat(3 - rowItems.size) {
                                Spacer(
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(1f)
                                )
                            }
                        }
                    }


                }
            }
        }


    }

}

@Composable
@Preview(device = "id:wearos_square", showBackground = true)
fun ContentFailedPreview(){
    ContentSuccess(
        emptyList()
    )
}
