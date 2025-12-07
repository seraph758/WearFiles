package com.dertefter.menu.presentation.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.lazy.rememberTransformationSpec
import com.google.android.horologist.compose.layout.ColumnItemType
import com.google.android.horologist.compose.layout.rememberResponsiveColumnPadding
import com.dertefter.design.components.items.TextItem

@Composable
fun ContentFailed(
    e: Exception? = null
){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){

        val columnState = rememberTransformingLazyColumnState()
        val contentPadding = rememberResponsiveColumnPadding(
            first = ColumnItemType.BodyText,
            last = ColumnItemType.Button,
        )

        val transformationSpec = rememberTransformationSpec()

        ScreenScaffold(
            scrollState = columnState, contentPadding = contentPadding
        )
        { contentPadding ->

            TransformingLazyColumn(
                state = columnState,
                contentPadding = contentPadding,
                modifier = Modifier.padding(4.dp)
            ) {
                item {
                    TextItem(
                        transformationSpec = transformationSpec,
                        text = e?.message ?: "Error"
                    )
                }

            }
        }

    }
}

@Composable
@Preview(device = "id:wearos_large_round", showBackground = true, showSystemUi = false)
fun ContentErrorPreview(){
    ContentFailed()
}