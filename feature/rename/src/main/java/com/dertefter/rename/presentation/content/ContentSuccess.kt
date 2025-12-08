package com.dertefter.rename.presentation.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.FilledIconButton
import androidx.wear.compose.material3.Icon
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.Text
import androidx.wear.compose.material3.lazy.rememberTransformationSpec
import com.dertefter.design.components.items.TextItem
import com.dertefter.design.theme.TheTheme
import com.dertefter.rename.presentation.Event
import com.google.android.horologist.compose.layout.ColumnItemType
import com.google.android.horologist.compose.layout.rememberResponsiveColumnPadding

@Composable
fun ContentSuccess(
    newFileName: String,
    isSaveEnabled: Boolean,
    onEvent: (Event) -> Unit,
) {

    val columnState = rememberTransformingLazyColumnState()
    val contentPadding = rememberResponsiveColumnPadding()

    val transformationSpec = rememberTransformationSpec()

    ScreenScaffold(
        scrollState = columnState, contentPadding = contentPadding
    )
    { contentPadding ->

        TransformingLazyColumn(
            state = columnState,
            contentPadding = contentPadding,
            modifier = Modifier
                .padding(horizontal = 10.dp),

            verticalArrangement = Arrangement.spacedBy(14.dp)


        ) {

            item {
                TextItem(
                    text = "Переименовать",
                    transformationSpec = transformationSpec,
                    textAlign = TextAlign.Center,
                )
            }

            item {
                OutlinedTextField(
                    value = newFileName,
                    onValueChange = { onEvent(Event.OnNewFileNameChanged(it)) },
                    shape = RoundedCornerShape(50),
                    label = { Text("Название") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                    )
                )
            }

            item {
                FilledIconButton(
                    onClick = { /*TODO*/ },
                    enabled = isSaveEnabled,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = null,
                    )
                }
            }


        }
    }
}

@Composable
@Preview(device = "id:wearos_small_round", showSystemUi = false, showBackground = true)
fun ContentFailedPreview(){
    TheTheme() {
        ContentSuccess(
            "33333",  true, {}
        )
    }
}