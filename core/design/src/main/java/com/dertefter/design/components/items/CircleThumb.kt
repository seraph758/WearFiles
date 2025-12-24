package com.dertefter.design.components.items

import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.decode.VideoFrameDecoder
import coil.request.ImageRequest
import coil.request.videoFrameMillis
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
    shape: RoundedCornerShape = CircleShape
) {
    val context = LocalContext.current

    var previewLoaded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .clip(shape)
            .clickable(onClick = onClick)
    ) {

        val resolvedIcon = icon ?: file?.resolveIcon()

        if (!previewLoaded) {
            Icon(
                contentDescription = null,
                imageVector = resolvedIcon ?: Icons.Draft,
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
                    .padding(6.dp),
                tint = iconColor
            )
        }

        val isVideo = remember(uri, file, context) {
            file?.let {
                val mime = MimeTypeMap.getSingleton()
                    .getMimeTypeFromExtension(it.extension.lowercase())
                mime?.startsWith("video/") == true
            } ?: uri?.let { u ->
                context.contentResolver.getType(u)?.startsWith("video/") == true
            } ?: false
        }

        val imageLoader = remember(context, isVideo) {
            if (isVideo) {
                ImageLoader.Builder(context)
                    .components { add(VideoFrameDecoder.Factory()) }
                    .build()
            } else {
                ImageLoader.Builder(context).build()
            }
        }

        val request = remember(uri, isVideo) {
            ImageRequest.Builder(context)
                .data(uri)
                .apply { if (isVideo) videoFrameMillis(0) }
                .crossfade(true)
                .build()
        }

        AsyncImage(
            model = request,
            imageLoader = imageLoader,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            onState = { state ->
                previewLoaded = state is AsyncImagePainter.State.Success
            }
        )
    }
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
