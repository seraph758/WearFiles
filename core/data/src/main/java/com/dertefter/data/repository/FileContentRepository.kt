package com.dertefter.data.repository

import android.net.Uri

interface FileContentRepository {

    suspend fun getTextContent(uri: Uri): String?
}