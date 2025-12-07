package com.dertefter.design.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.TransformingLazyColumnItemScope
import androidx.wear.compose.material3.Card
import androidx.wear.compose.material3.CardDefaults
import androidx.wear.compose.material3.Icon
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.SurfaceTransformation
import androidx.wear.compose.material3.Text
import androidx.wear.compose.material3.lazy.TransformationSpec
import androidx.wear.compose.material3.lazy.rememberTransformationSpec
import androidx.wear.compose.material3.lazy.transformedHeight

@Composable
fun TransformingLazyColumnItemScope.FileItemHigh(
    transformationSpec: TransformationSpec,
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .transformedHeight(this, transformationSpec),
        transformation = SurfaceTransformation(transformationSpec),
        onClick = onClick,
        shape = RoundedCornerShape(50),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        contentPadding = PaddingValues(0.dp),

    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                Modifier
                    .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(50))
                    .padding(6.dp)
                ,
                tint = MaterialTheme.colorScheme.onPrimary
            )

            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(device = "id:wearos_small_round", showBackground = true, showSystemUi = false)
@Composable
private fun FileItemHighPreview() {
    val transformationSpec = rememberTransformationSpec()
    TransformingLazyColumn {
        item {
            FileItemHigh(
                transformationSpec = transformationSpec,
                text = "Preview Tex gdgdgdgdgdgt",
                icon = Icons.Filled.Folder,
                onClick = {}
            )
        }
    }
}
