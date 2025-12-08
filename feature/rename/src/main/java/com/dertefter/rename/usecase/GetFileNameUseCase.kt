package com.dertefter.rename.usecase

import com.dertefter.data.repository.FileManagerRepository
import java.io.File
import javax.inject.Inject

class GetFileNameUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {
    operator fun invoke(path: String): Result<String> {
        return fileManagerRepository.getFileName(path)
    }
}