package com.dertefter.design.components.basic_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.FilledIconButton
import androidx.wear.compose.material3.FilledTonalIconButton
import androidx.wear.compose.material3.Icon
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.lazy.rememberTransformationSpec
import com.dertefter.design.components.items.TextItem

@Composable
fun DialogDefaultScreen(
    title: String? = null,
    onCancel: () -> Unit,
    onOk: () -> Unit,
    cancelIcon: ImageVector,
    okIcon: ImageVector
){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){

        val columnState = rememberTransformingLazyColumnState()
        val contentPadding = PaddingValues(
            top = 28.dp, start = 10.dp, end = 10.dp, bottom = 72.dp
        )

        val transformationSpec = rememberTransformationSpec()

        ScreenScaffold(
            scrollState = columnState, contentPadding = contentPadding
        )
        {
                contentPadding ->

            TransformingLazyColumn(
                state = columnState,
                contentPadding = contentPadding,
                modifier = Modifier.padding(top = 28.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                title?.let {
                    item {
                        TextItem(
                            transformationSpec = transformationSpec,
                            text = title,
                            textColor = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center,
                            textStyle = MaterialTheme.typography.titleSmall
                        )
                    }
                }


                item {

                    Row(){

                        FilledIconButton(
                            onClick = {
                                onCancel()
                            },
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Icon(
                                imageVector = cancelIcon,
                                contentDescription = null,
                            )
                        }

                        FilledTonalIconButton(
                            onClick = {
                                onOk()
                            },
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Icon(
                                imageVector = okIcon,
                                contentDescription = null,
                            )
                        }
                    }


                }


            }
        }

    }
}
