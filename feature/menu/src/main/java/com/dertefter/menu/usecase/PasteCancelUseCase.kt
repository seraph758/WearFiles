package com.dertefter.menu.usecase

import com.dertefter.data.model.PinnedItem
import com.dertefter.data.repository.ClipboardRepository
import com.dertefter.data.repository.PinnedRepository
import java.io.File
import javax.inject.Inject

class PasteCancelUseCase @Inject constructor(
    private val clipboardRepository: ClipboardRepository
) {
     operator fun invoke() {
        return clipboardRepository.cancel()
    }
}