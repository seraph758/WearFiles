package com.dertefter.file_list.presentation.content

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
import com.dertefter.file_list.presentation.Action
import com.dertefter.file_list.presentation.Event
import com.dertefter.file_list.utils.isImage
import com.dertefter.file_list.utils.loadThumbnail
import com.dertefter.menu.MenuMode
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun ContentSuccess(
    path: PrettyPath,
    files: List<File>,
    onEvent: (Event) -> Unit,
    actions: List<Action> = emptyList()
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

            item {

                val fullPath = path.path
                val homePath = path.homePath


                PathItem(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 14.dp),
                    transformationSpec = transformationSpec,
                    fullPath = fullPath,
                    homePath = homePath,
                    homeIcon = Icons.Default.Watch,
                )
            }

           for (file in files){

               item {

                   var thumbnail by remember { mutableStateOf<ImageBitmap?>(null) }
                   val scope = rememberCoroutineScope()

                   if (file.isImage()) {
                       LaunchedEffect(file) {
                           scope.launch {
                               file.loadThumbnail()?.let {
                                   thumbnail = it.asImageBitmap()
                               }
                           }
                       }
                   }

                   val icon =
                       if (file.isDirectory) Icons.Filled.Folder else Icons.AutoMirrored.Filled.InsertDriveFile
                   FileItem(
                       transformationSpec, 
                       text = file.name, 
                       icon = icon,
                       thumbnail = thumbnail,
                       onClick = {
                           if (file.isFile) {
                               onEvent(Event.OnFileClick(file))
                           } else if (file.isDirectory) {
                               onEvent(Event.OnDirectoryClick(file.absolutePath))
                           } else {

                           }
                       },

                       onLongClick = {
                           onEvent(Event.OnShowMenuFor(file.absolutePath, menuMode = MenuMode.OUTSIDE))
                       }

                   )
               }
           }

            item {
                BottomBarItem(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 14.dp),
                    transformationSpec = transformationSpec,
                    onUpClick = if (actions.contains(Action.MOVE_BACK)) {
                        { onEvent(Event.OnNavigateBack) }
                    } else { null },
                    onMoreClick = if (actions.contains(Action.MORE)) {
                        { onEvent(Event.OnShowMenuFor(path = path.path, menuMode = MenuMode.INSIDE)) }
                    } else { null }

                )
            }

        }

    }

}

@Composable
@Preview(device = "id:wearos_square", showBackground = true)
fun ContentFailedPreview(){
    ContentSuccess(
        PrettyPath("", ""),
        files = emptyList(),
        onEvent = {}
    )
}
