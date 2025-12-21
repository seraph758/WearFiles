package com.dertefter.design.components.common

import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dertefter.design.icons.Icons
import java.io.File


@Composable
fun CircleThumb(
    uri: Uri?,
    modifier: Modifier = Modifier,
    file: File? = null,
    contentDescription: String? = null,
    onClick: () -> Unit = {},
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    iconColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    icon: ImageVector? = null,
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(backgroundColor)
    ){

        val icon = icon ?: file?.resolveIcon()

        Icon(
            contentDescription = "",
            imageVector = icon ?: Icons.Draft,
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            tint = iconColor
        )

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(uri)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )







    }

}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun CircleThumbPreview(){
    CircleThumb(
        uri = "https://picsum.photos/200/300".toUri(),
        modifier = Modifier.size(64.dp),
    )
}


@Composable
fun File.resolveIcon(): ImageVector {
    if (isDirectory) return Icons.Folder

    val mime = MimeTypeMap.getSingleton()
        .getMimeTypeFromExtension(extension.lowercase())
        ?: return Icons.Draft

    return when {
        mime.startsWith("image/") -> Icons.Image
        mime.startsWith("video/") -> Icons.Video
        mime.startsWith("audio/") -> Icons.Music
        mime.contains("zip") || mime.contains("x-rar") || mime.contains("x-7z") -> Icons.Zip
        mime.endsWith("vnd.android.package-archive") -> Icons.Apk
        mime.startsWith("text/") || mime.endsWith("json") || mime.endsWith("xml") || mime.endsWith("csv") -> Icons.Docs
        else -> Icons.Draft
    }
}