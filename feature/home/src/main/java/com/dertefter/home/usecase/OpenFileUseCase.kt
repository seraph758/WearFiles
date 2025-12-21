package com.dertefter.home.usecase

import com.dertefter.data.repository.FileInteractionHandler
import java.io.File
import javax.inject.Inject

class OpenFileUseCase @Inject constructor(
    private val fileInteractionHandler: FileInteractionHandler
) {
    operator fun invoke(path: String) {
        return fileInteractionHandler.openFileByPath(path)
    }
}