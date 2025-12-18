package com.dertefter.gallery.data

data class MediaItem(
    val id: Long,
    val uri: String,
    val displayName: String?,
    val isVideo: Boolean
)