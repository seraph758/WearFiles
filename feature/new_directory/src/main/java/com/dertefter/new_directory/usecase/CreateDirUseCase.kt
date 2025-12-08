package com.dertefter.new_directory.usecase

import com.dertefter.data.repository.FileManagerRepository
import java.io.File
import javax.inject.Inject

class CreateDirUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {
    operator fun invoke(path: String, newName: String): Result<Boolean> {
        return fileManagerRepository.createDirectory(path, newName)
    }
}