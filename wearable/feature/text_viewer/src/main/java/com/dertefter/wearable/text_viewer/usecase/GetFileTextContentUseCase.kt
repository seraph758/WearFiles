package com.dertefter.wearable.text_viewer.usecase

import androidx.core.net.toUri
import com.dertefter.wearable.data.repository.FileContentRepository
import javax.inject.Inject

class GetFileTextContentUseCase @Inject constructor(
    val fileContentRepository: FileContentRepository
) {
    suspend operator fun invoke(uriString: String): String {
        val uri = uriString.toUri()
        return fileContentRepository.getTextContent(uri)
    }
}