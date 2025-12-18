package com.dertefter.gallery.usecase

import android.content.ContentResolver
import android.content.ContentUris
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.dertefter.gallery.data.MediaItem
import javax.inject.Inject

class GetMediaUseCase @Inject constructor(
    private val contentResolver: ContentResolver
) {

    operator fun invoke(limit: Int = 100): Result<List<MediaItem>> {
        return try {
            val result = mutableListOf<MediaItem>()

            result += queryImages(limit)
            result += queryVideos(limit)

            val sorted = result.sortedByDescending { it.id }
                .take(limit)

            Log.e("sorted", "$sorted")

            Result.success(sorted)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun queryImages(limit: Int): List<MediaItem> {
        val list = mutableListOf<MediaItem>()

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME
        )

        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val cursor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val args = Bundle().apply {
                putStringArray(
                    ContentResolver.QUERY_ARG_SORT_COLUMNS,
                    arrayOf(MediaStore.Images.Media._ID)
                )
                putInt(
                    ContentResolver.QUERY_ARG_SORT_DIRECTION,
                    ContentResolver.QUERY_SORT_DIRECTION_DESCENDING
                )
                putInt(ContentResolver.QUERY_ARG_LIMIT, limit)
            }
            contentResolver.query(uri, projection, args, null)
        } else {
            val sort = "${MediaStore.Images.Media._ID} DESC LIMIT $limit"
            contentResolver.query(uri, projection, null, null, sort)
        }

        cursor?.use { c ->
            val idIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)

            while (c.moveToNext()) {
                val id = c.getLong(idIndex)
                val name = c.getString(nameIndex)

                val contentUri =
                    ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)

                list.add(
                    MediaItem(
                        id = id,
                        uri = contentUri.toString(),
                        displayName = name,
                        isVideo = false
                    )
                )
            }
        }

        return list
    }

    private fun queryVideos(limit: Int): List<MediaItem> {
        val list = mutableListOf<MediaItem>()

        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME
        )

        val uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI

        val cursor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val args = Bundle().apply {
                putStringArray(
                    ContentResolver.QUERY_ARG_SORT_COLUMNS,
                    arrayOf(MediaStore.Video.Media._ID)
                )
                putInt(
                    ContentResolver.QUERY_ARG_SORT_DIRECTION,
                    ContentResolver.QUERY_SORT_DIRECTION_DESCENDING
                )
                putInt(ContentResolver.QUERY_ARG_LIMIT, limit)
            }
            contentResolver.query(uri, projection, args, null)
        } else {
            val sort = "${MediaStore.Video.Media._ID} DESC LIMIT $limit"
            contentResolver.query(uri, projection, null, null, sort)
        }

        cursor?.use { c ->
            val idIndex = c.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val nameIndex = c.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)

            while (c.moveToNext()) {
                val id = c.getLong(idIndex)
                val name = c.getString(nameIndex)

                val contentUri =
                    ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id)

                list.add(
                    MediaItem(
                        id = id,
                        uri = contentUri.toString(),
                        displayName = name,
                        isVideo = true
                    )
                )
            }
        }

        return list
    }
}
