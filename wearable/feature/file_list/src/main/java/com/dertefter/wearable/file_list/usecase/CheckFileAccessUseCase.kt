package com.dertefter.wearable.file_list.usecase

import com.dertefter.wearable.data.repository.FileManagerRepository
import javax.inject.Inject

class CheckFileAccessUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {
    operator fun invoke(): Boolean {
        return fileManagerRepository.hasFileAccess()
    }
}