package com.dertefter.home.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.InsertDriveFile
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Watch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.lazy.rememberTransformationSpec
import com.dertefter.data.model.PrettyPath
import com.dertefter.design.components.items.BottomBarItem
import com.dertefter.design.components.items.FileItem
import com.dertefter.design.components.items.PathItem
import com.dertefter.home.data.model.Pinned
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun ContentSuccess(
    pinned: List<Pinned>
) {

    val columnState = rememberTransformingLazyColumnState()

    val transformationSpec = rememberTransformationSpec()

    ScreenScaffold(
        scrollState = columnState,
        contentPadding = PaddingValues(
            top = 52.dp, start = 10.dp, end = 10.dp, bottom = 10.dp
        ),
    )
    { contentPadding ->

        TransformingLazyColumn(
            state = columnState,
            contentPadding = contentPadding,
        ) {

           for (item in pinned){

               item {

                   FileItem(
                       transformationSpec, 
                       text = item.type.name,
                       icon = Icons.Default.Watch,
                       onClick = {

                       },
                       onLongClick = {
                               }

                   )
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
