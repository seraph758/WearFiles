package com.dertefter.wearable.data.repository

import android.net.Uri

interface FileInteractionHandler {

    fun openFileByPath(path: String)

    fun openFileByUri(uri: Uri)
}
