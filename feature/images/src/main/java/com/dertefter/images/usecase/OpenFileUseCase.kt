package com.dertefter.images.usecase

import android.net.Uri
import com.dertefter.data.repository.FileInteractionHandler
import javax.inject.Inject

class OpenFileUseCase @Inject constructor(
    private val fileInteractionHandler: FileInteractionHandler
) {
    operator fun invoke(uri: Uri) {
        return fileInteractionHandler.openFileByUri(uri)
    }
}