package com.dertefter.file_list.presentation.content

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.InsertDriveFile
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Watch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.google.android.horologist.compose.layout.ColumnItemType
import com.google.android.horologist.compose.layout.rememberResponsiveColumnPadding
import java.io.File

@Composable
fun ContentSuccess(
    path: PrettyPath,
    files: List<File>,
    onEvent: (Event) -> Unit,
    actions: List<Action> = emptyList()
) {

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
                   val icon =
                       if (file.isDirectory) Icons.Filled.Folder else Icons.AutoMirrored.Filled.InsertDriveFile
                   FileItem(
                       transformationSpec, 
                       text = file.name, 
                       icon = icon,
                       onClick = {
                           if (file.isFile) {
                               onEvent(Event.OnFileClick(file))
                           } else if (file.isDirectory) {
                               onEvent(Event.OnDirectoryClick(file.absolutePath))
                           } else {

                           }
                       },

                       onLongClick = {
                           onEvent(Event.OnNavigateToMenu(file.absolutePath))
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
                        { onEvent(Event.OnNavigateToMenu(path = path.path)) }
                    } else { null }

                )
            }

        }

    }

}

@Composable
@Preview
fun ContentFailedPreview(){
    ContentSuccess(
        PrettyPath("", ""),
        files = emptyList(),
        onEvent = {}
    )
}
