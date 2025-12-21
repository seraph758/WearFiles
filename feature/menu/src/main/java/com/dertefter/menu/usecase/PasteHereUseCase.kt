package com.dertefter.menu.usecase

import com.dertefter.data.model.PinnedItem
import com.dertefter.data.repository.ClipboardRepository
import com.dertefter.data.repository.PinnedRepository
import java.io.File
import javax.inject.Inject

class PasteHereUseCase @Inject constructor(
    private val clipboardRepository: ClipboardRepository
) {
    suspend operator fun invoke(path: String): Boolean {
        return clipboardRepository.insertTo(path)
    }
}