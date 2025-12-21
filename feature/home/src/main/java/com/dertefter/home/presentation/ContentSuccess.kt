package com.dertefter.home.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.lazy.rememberTransformationSpec
import com.dertefter.design.components.items.FileItem
import com.dertefter.design.components.items.FileItemType
import com.dertefter.design.icons.Icons
import com.dertefter.home.R
import com.dertefter.home.data.model.Pinned
import com.dertefter.home.data.model.PinnedType

@Composable
fun ContentSuccess(
    pinned: List<Pinned>,
    onEvent: (Event) -> Unit,
) {

    val columnState = rememberTransformingLazyColumnState()

    val transformationSpec = rememberTransformationSpec()

    ScreenScaffold(
        scrollState = columnState,
        contentPadding = PaddingValues(
            top = 52.dp, start = 10.dp, end = 10.dp, bottom = 10.dp
        ),
    ) { contentPadding ->

        TransformingLazyColumn(
            state = columnState,
            contentPadding = contentPadding,
        ) {

            for (item in pinned) {

                item {

                    val title = when (item.type){
                        PinnedType.AUDIO -> stringResource(R.string.music)
                        PinnedType.MEDIA -> stringResource(R.string.media)
                        PinnedType.DOCUMENTS -> ""
                        PinnedType.CUSTOM -> ""
                        PinnedType.STORAGE -> stringResource(R.string.storage)
                    }

                    val onClickEvent = when (item.type){
                        PinnedType.MEDIA -> Event.OnNavigateToGallery
                        PinnedType.STORAGE -> Event.OnNavigateToStorage

                        else ->  Event.OnNavigateToGallery
                    }

                    FileItem(
                        transformationSpec,
                        text = title,
                        icon = Icons.Wallpaper,
                        onClick = {
                            onEvent(onClickEvent)
                        },
                        onLongClick = {},
                        type = FileItemType.PRIMARY

                    )
                }
            }

        }

    }

}

@Composable
@Preview(device = "id:wearos_square", showBackground = true)
fun ContentFailedPreview() {
    ContentSuccess(
        emptyList(),{}
    )
}
