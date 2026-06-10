package com.dertefter.pdf_viewer.usecase

import androidx.core.net.toUri
import com.dertefter.data.repository.FileContentRepository
import javax.inject.Inject

class GetFileNameUseCase @Inject constructor(
    val fileContentRepository: FileContentRepository
) {
    suspend operator fun invoke(uriString: String): String {
        val uri = uriString.toUri()
        return fileContentRepository.getFileName(uri)
    }
}