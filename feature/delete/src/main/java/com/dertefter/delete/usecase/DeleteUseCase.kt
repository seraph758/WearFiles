package com.dertefter.delete.usecase

import com.dertefter.data.repository.FileManagerRepository
import javax.inject.Inject

class DeleteUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {
    suspend operator fun invoke(paths: List<String>): Result<Boolean> {
        return fileManagerRepository.delete(paths)
    }
}