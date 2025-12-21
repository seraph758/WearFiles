package com.dertefter.design.components.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.TransformingLazyColumnItemScope
import androidx.wear.compose.material3.*
import androidx.wear.compose.material3.lazy.TransformationSpec
import androidx.wear.compose.material3.lazy.rememberTransformationSpec
import androidx.wear.compose.material3.lazy.transformedHeight
import com.dertefter.design.components.common.CircleThumb
import java.io.File

enum class FileItemType {
    DEFAULT, ERROR, PRIMARY
}

@Composable
fun TransformingLazyColumnItemScope.FileItem(
    transformationSpec: TransformationSpec,
    modifier: Modifier = Modifier,
    text: String,
    thumbnailUrl: String? = null,
    onClick: () -> Unit,
    onLongClick: () -> Unit = {},
    file: File? = null,
    type: FileItemType = FileItemType.DEFAULT,
    icon: ImageVector? = null
) {


    val config = LocalConfiguration.current
    val isRound: Boolean = config.isScreenRound

    val height = 48.dp

    val outsideRadius = if (isRound) {
        height/2
    } else {
        10.dp
    }

    val contentPadding = if (isRound) {
        6.dp
    } else {
        4.dp
    }

    val insideRadius = outsideRadius - contentPadding

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
            .height(height)
            .transformedHeight(this, transformationSpec),
        transformation = SurfaceTransformation(transformationSpec),
        onClick = onClick,
        onLongClick = onLongClick,
        shape = RoundedCornerShape(outsideRadius),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        contentPadding = PaddingValues(0.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            CircleThumb(
                uri = thumbnailUrl?.toUri(),
                file = file,
                modifier = Modifier
                    .size(height - contentPadding*2)
                ,
                icon = icon,
                iconColor = iconTint,
                backgroundColor = iconBackgroundColor
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
                text = "Preview Text Example",
                thumbnailUrl = "",
                onClick = {}
            )
        }
    }
}