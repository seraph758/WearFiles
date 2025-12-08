package com.dertefter.menu.presentation.content

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CreateNewFolder
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Folder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.lazy.rememberTransformationSpec
import com.dertefter.design.components.items.FileItem
import com.dertefter.design.components.items.FileItemHigh
import com.dertefter.design.components.items.FileItemType
import com.dertefter.menu.R
import com.dertefter.menu.presentation.Event
import com.dertefter.menu.presentation.MenuAction
import com.dertefter.menu.presentation.MenuActionType
import com.google.android.horologist.compose.layout.ColumnItemType
import com.google.android.horologist.compose.layout.rememberResponsiveColumnPadding

@Composable
fun ContentSuccess(
    name: String,
    actions: List<MenuAction> = emptyList(),
    onEvent: (Event) -> Unit,
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

            items(actions) { action ->

                val text = when (action.type) {
                    MenuActionType.DELETE -> stringResource(R.string.delete)
                    MenuActionType.RENAME -> stringResource(R.string.rename)
                    MenuActionType.NEW_DIR -> stringResource(R.string.new_dir)
                    MenuActionType.OPEN -> name
                }

                val icon = when (action.type) {
                    MenuActionType.DELETE -> Icons.Default.Delete
                    MenuActionType.RENAME -> Icons.Default.Edit
                    MenuActionType.NEW_DIR -> Icons.Default.CreateNewFolder
                    MenuActionType.OPEN -> Icons.AutoMirrored.Filled.ArrowForward
                }

                val event: Event = when (action.type) {
                    MenuActionType.DELETE -> Event.OnNavigateToRename(action.path)
                    MenuActionType.RENAME -> Event.OnNavigateToRename(action.path)
                    MenuActionType.NEW_DIR -> Event.OnNavigateToNewDirectory(action.path)
                    MenuActionType.OPEN -> Event.OnNavigateToNewDirectory(action.path)
                }

                val type = when (action.type) {
                    MenuActionType.DELETE -> FileItemType.ERROR
                    MenuActionType.OPEN -> FileItemType.PRIMARY
                    else -> FileItemType.DEFAULT
                }

                FileItem(
                    transformationSpec = transformationSpec,
                    text = text,
                    icon = icon,
                    onClick = { onEvent(event) },
                    type = type
                )
            }
        }


    }

}

@Composable
@Preview(device = "id:wearos_small_round", showSystemUi = false, showBackground = true)
fun ContentFailedPreview(){
    ContentSuccess(
"",
        emptyList(),
        onEvent = {}
    )
}
