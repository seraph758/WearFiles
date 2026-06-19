package com.dertefter.wearable.rename.usecase

import com.dertefter.wearable.data.repository.FileManagerRepository
import javax.inject.Inject

class GetFileNameUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {
    operator fun invoke(path: String): Result<String> {
        return fileManagerRepository.getFileName(path)
    }
}