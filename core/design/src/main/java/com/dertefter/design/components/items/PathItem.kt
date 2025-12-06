package com.dertefter.design.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Watch
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.TransformingLazyColumnItemScope
import androidx.wear.compose.material3.Card
import androidx.wear.compose.material3.CardDefaults
import androidx.wear.compose.material3.ColorScheme
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.SurfaceTransformation
import androidx.wear.compose.material3.Text
import androidx.wear.compose.material3.lazy.TransformationSpec
import androidx.wear.compose.material3.lazy.rememberTransformationSpec
import androidx.wear.compose.material3.lazy.transformedHeight
import com.dertefter.design.theme.TheTheme

@Composable
fun TransformingLazyColumnItemScope.PathItem(
    transformationSpec: TransformationSpec,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Outlined.Watch,
    pathText: String
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 1.dp)
            .clickable(
                enabled = false,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ){}
            .transformedHeight(this, transformationSpec),
        transformation = SurfaceTransformation(transformationSpec),
        onClick = {},
        enabled = false,
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(0.dp)
    )
    {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                modifier = Modifier.size(14.dp),
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(
                modifier = Modifier
                    .width(6.dp)
            )

            Text(
                text = pathText,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodySmall
            )
        }


    }
}

@Preview(device = "id:wearos_small_round", showBackground = true, showSystemUi = false)
@Composable
private fun PathItemPreview() {
    val transformationSpec = rememberTransformationSpec()
    TransformingLazyColumn {
        item {
            PathItem(
                transformationSpec = transformationSpec,
                pathText = "Preview Text httr fhf hf hfhf f hfh",
            )
        }
    }
}
