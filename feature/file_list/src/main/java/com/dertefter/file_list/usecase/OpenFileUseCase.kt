package com.dertefter.file_list.usecase

import com.dertefter.data.repository.FileInteractionHandler
import com.dertefter.data.repository.FileManagerRepository
import java.io.File
import javax.inject.Inject

class OpenFileUseCase @Inject constructor(
    private val fileInteractionHandler: FileInteractionHandler
) {
    operator fun invoke(file: File) {
        return fileInteractionHandler.openFile(file.absolutePath)
    }
}