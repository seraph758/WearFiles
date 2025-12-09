package com.dertefter.file_list.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

fun File.isImage(): Boolean {
    val imageExtensions = listOf("jpg", "jpeg", "png", "gif", "bmp", "webp")
    return imageExtensions.any { name.endsWith(it, ignoreCase = true) }
}

suspend fun File.loadThumbnail(maxSize: Int = 248): Bitmap? = withContext(Dispatchers.IO) {
    if (!exists() || !isFile) return@withContext null

    try {
        val options = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
        }
        BitmapFactory.decodeFile(absolutePath, options)
        val (width, height) = options.outWidth to options.outHeight
        var scale = 1
        if (width > maxSize || height > maxSize) {
            scale = maxOf(width / maxSize, height / maxSize)
        }

        return@withContext BitmapFactory.Options().run {
            inSampleSize = scale
            inJustDecodeBounds = false
            BitmapFactory.decodeFile(absolutePath, this)
        }
    } catch (e: Exception) {
        null
    }
}