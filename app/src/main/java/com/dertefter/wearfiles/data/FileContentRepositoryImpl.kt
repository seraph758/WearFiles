package com.dertefter.wearfiles.data

import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import com.dertefter.data.repository.FileContentRepository
import java.io.File
import javax.inject.Inject

class FileContentRepositoryImpl @Inject constructor(
    private val contentResolver: ContentResolver
) : FileContentRepository {

    override suspend fun getTextContent(uri: Uri): String? {
        val contents = contentResolver.openInputStream(uri)?.bufferedReader()?.readText()
        return contents
    }


}