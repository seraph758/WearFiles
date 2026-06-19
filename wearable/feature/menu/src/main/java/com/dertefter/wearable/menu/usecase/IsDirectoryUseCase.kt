package com.dertefter.wearable.menu.usecase

import com.dertefter.wearable.data.repository.FileManagerRepository
import javax.inject.Inject

class IsDirectoryUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {
    suspend operator fun invoke(path: String): Boolean {
        return fileManagerRepository.isDirectory(path)
    }
}

