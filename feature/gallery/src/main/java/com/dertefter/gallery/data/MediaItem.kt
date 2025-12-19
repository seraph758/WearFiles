package com.dertefter.gallery.data

import android.net.Uri

data class MediaItem(
    val id: Long,
    val uri: Uri,
    val displayName: String?,
    val isVideo: Boolean
)