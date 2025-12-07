package com.dertefter.file_list.usecase

import com.dertefter.data.repository.FileManagerRepository
import javax.inject.Inject

class CheckIfNavigateUpAvailableUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {
    operator fun invoke(path: String): Boolean {
        return fileManagerRepository.canNavigateUpFrom(
            path = path
        )
    }
}