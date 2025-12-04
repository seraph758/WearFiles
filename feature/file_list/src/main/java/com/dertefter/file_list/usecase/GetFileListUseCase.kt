package com.dertefter.file_list.usecase

import com.dertefter.data.repository.FileManagerRepository
import java.io.File
import javax.inject.Inject

class GetFileListUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {
    suspend operator fun invoke(path: String): List<File> {
        return fileManagerRepository.getFiles(path)
    }
}