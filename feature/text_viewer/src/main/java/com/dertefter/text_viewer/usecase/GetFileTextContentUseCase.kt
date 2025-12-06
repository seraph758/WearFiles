package com.dertefter.text_viewer.usecase

import androidx.core.net.toUri
import com.dertefter.data.repository.FileContentRepository
import com.dertefter.data.repository.FileManagerRepository
import java.io.File
import javax.inject.Inject

class GetFileTextContentUseCase @Inject constructor(
    val fileContentRepository: FileContentRepository
) {
    suspend operator fun invoke(uriString: String): String? {
        val uri = uriString.toUri()
        return fileContentRepository.getTextContent(uri)
    }
}