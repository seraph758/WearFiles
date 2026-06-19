package com.dertefter.wearable.menu.usecase

import com.dertefter.wearable.data.repository.ClipboardRepository
import javax.inject.Inject

class PasteHereUseCase @Inject constructor(
    private val clipboardRepository: ClipboardRepository
) {
    suspend operator fun invoke(path: String): Boolean {
        return clipboardRepository.insertTo(path)
    }
}