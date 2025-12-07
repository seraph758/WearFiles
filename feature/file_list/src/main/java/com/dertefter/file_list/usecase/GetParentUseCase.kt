package com.dertefter.file_list.usecase

import com.dertefter.data.repository.FileManagerRepository
import java.io.File
import javax.inject.Inject

class GetParentUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {
    suspend operator fun invoke(path: String): String? {
        return fileManagerRepository.getParentFilePath(
            path = path
        )
    }
}