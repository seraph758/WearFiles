package com.dertefter.wearable.delete.usecase

import com.dertefter.wearable.data.repository.FileManagerRepository
import javax.inject.Inject

class DeleteUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {
    suspend operator fun invoke(paths: List<String>): Result<Boolean> {
        return fileManagerRepository.delete(paths)
    }
}