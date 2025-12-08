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


enum class FileItemType(){
    DEFAULT, ERROR, PRIMARY
}

@Composable
fun TransformingLazyColumnItemScope.FileItem(
    transformationSpec: TransformationSpec,
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    onLongClick: () -> Unit = {},
    type: FileItemType = FileItemType.DEFAULT
) {

    val containerColor = when (type) {
        FileItemType.DEFAULT -> MaterialTheme.colorScheme.surfaceContainer
        FileItemType.ERROR -> MaterialTheme.colorScheme.errorContainer
        FileItemType.PRIMARY -> MaterialTheme.colorScheme.primaryContainer
    }

    val textColor = when (type) {
        FileItemType.DEFAULT -> MaterialTheme.colorScheme.onSurface
        FileItemType.ERROR -> MaterialTheme.colorScheme.onErrorContainer
        FileItemType.PRIMARY -> MaterialTheme.colorScheme.onPrimaryContainer
    }

    val iconTint = when (type) {
        FileItemType.DEFAULT -> MaterialTheme.colorScheme.onSecondary
        FileItemType.ERROR -> MaterialTheme.colorScheme.onError
        FileItemType.PRIMARY -> MaterialTheme.colorScheme.onPrimary
    }

    val iconBackgroundColor = when (type) {
        FileItemType.DEFAULT -> MaterialTheme.colorScheme.secondary
        FileItemType.ERROR -> MaterialTheme.colorScheme.error
        FileItemType.PRIMARY -> MaterialTheme.colorScheme.primary
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .transformedHeight(this, transformationSpec),
        transformation = SurfaceTransformation(transformationSpec),
        onClick = onClick,
        onLongClick = onLongClick,
        shape = RoundedCornerShape(50),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
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
                    color = iconBackgroundColor,
                    shape = RoundedCornerShape(50))
                    .padding(6.dp)
                ,
                tint = iconTint
            )

            Text(
                text = text,
                color = textColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(device = "id:wearos_small_round", showBackground = true, showSystemUi = false)
@Composable
private fun FileItemPreview() {
    val transformationSpec = rememberTransformationSpec()
    TransformingLazyColumn {
        item {
            FileItem(
                transformationSpec = transformationSpec,
                text = "Preview Tex gdgdgdgdgdgt",
                icon = Icons.Filled.Folder,
                onClick = {}
            )
        }
    }
}
