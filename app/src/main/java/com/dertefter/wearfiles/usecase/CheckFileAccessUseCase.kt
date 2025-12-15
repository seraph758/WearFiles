package com.dertefter.wearfiles.usecase

import com.dertefter.data.repository.FileManagerRepository
import javax.inject.Inject

class CheckFileAccessUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {
    operator fun invoke(): Boolean {
        return fileManagerRepository.hasFileAccess()
    }
}