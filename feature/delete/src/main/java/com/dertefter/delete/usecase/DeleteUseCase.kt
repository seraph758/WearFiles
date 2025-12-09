package com.dertefter.delete.usecase

import com.dertefter.data.repository.FileManagerRepository
import javax.inject.Inject

class DeleteUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {
    operator fun invoke(path: String): Result<Boolean> {
        return fileManagerRepository.delete(path)
    }
}