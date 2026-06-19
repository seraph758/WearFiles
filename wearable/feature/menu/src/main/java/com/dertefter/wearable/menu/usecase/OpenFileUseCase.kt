package com.dertefter.wearable.menu.usecase

import com.dertefter.wearable.data.repository.FileInteractionHandler
import javax.inject.Inject

class OpenFileUseCase @Inject constructor(
    private val fileInteractionHandler: FileInteractionHandler
) {
    operator fun invoke(path: String) {
        return fileInteractionHandler.openFileByPath(path)
    }
}