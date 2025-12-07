package com.dertefter.file_list.usecase

import com.dertefter.data.repository.FileManagerRepository
import java.io.File
import javax.inject.Inject

class CheckIfNavigateUpAvaibleUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {
    operator fun invoke(path: String): Boolean {
        return fileManagerRepository.canNavigateUpFrom(
            path = path
        )
    }
}