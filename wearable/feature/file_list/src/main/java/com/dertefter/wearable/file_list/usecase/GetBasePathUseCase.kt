package com.dertefter.wearable.file_list.usecase

import com.dertefter.wearable.data.repository.FileManagerRepository
import javax.inject.Inject

class GetBasePathUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {
    operator fun invoke(): String {
        return fileManagerRepository.getBasePath()
    }
}