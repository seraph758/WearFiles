package com.dertefter.wearable.menu.usecase

import com.dertefter.wearable.data.repository.ClipboardRepository
import javax.inject.Inject

class PasteCancelUseCase @Inject constructor(
    private val clipboardRepository: ClipboardRepository
) {
     operator fun invoke() {
        return clipboardRepository.cancel()
    }
}