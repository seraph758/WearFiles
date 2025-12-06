package com.dertefter.onboarding.presentation.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.FilledTonalButton
import androidx.wear.compose.material3.Icon
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.SurfaceTransformation
import androidx.wear.compose.material3.Text
import androidx.wear.compose.material3.lazy.rememberTransformationSpec
import androidx.wear.compose.material3.lazy.transformedHeight
import com.dertefter.design.components.items.BadgedTextItem
import com.dertefter.design.components.items.CodeItem
import com.dertefter.design.components.items.TextItem
import com.dertefter.onboarding.R
import com.dertefter.onboarding.presentation.Event
import com.google.android.horologist.compose.layout.ColumnItemType
import com.google.android.horologist.compose.layout.rememberResponsiveColumnPadding

@Composable
fun ContentFailed(onEvent: (Event) -> Unit){

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
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 8.dp),

            verticalArrangement = Arrangement.spacedBy(14.dp)


        ) {


            item {
                TextItem(
                    transformationSpec, text = stringResource(R.string.permission_alert)
                )
            }

            item {
                BadgedTextItem(
                    transformationSpec, text = stringResource(R.string.pt1), badgeText = "1"
                )
            }

            item {
                BadgedTextItem(
                    transformationSpec, text = stringResource(R.string.pt2), badgeText = "2"
                )
            }

            item {
                CodeItem(
                    transformationSpec, text = stringResource(R.string.pt2_command)
                )
            }

            item {
                BadgedTextItem(
                    transformationSpec, text = stringResource(R.string.pt3), badgeText = "3"
                )
            }

            item {
                FilledTonalButton(
                    onClick = { onEvent(Event.OnOpenLinkOnPhone) },
                    modifier = Modifier.transformedHeight(this, transformationSpec),
                    transformation = SurfaceTransformation(transformationSpec),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.PhoneAndroid,
                            contentDescription = "",
                        )
                    }) {
                    Text(
                        text = stringResource(R.string.info)
                    )
                }
            }


        }

    }

}

@Composable
@Preview()
fun ContentFailedPreview(){
    ContentFailed({})
}