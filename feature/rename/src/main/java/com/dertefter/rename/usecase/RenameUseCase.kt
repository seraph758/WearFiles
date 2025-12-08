package com.dertefter.rename.usecase

import com.dertefter.data.repository.FileManagerRepository
import java.io.File
import javax.inject.Inject

class RenameUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {
    suspend operator fun invoke(path: String, newName: String): Result<Boolean> {
        return fileManagerRepository.rename(path, newName)
    }
}