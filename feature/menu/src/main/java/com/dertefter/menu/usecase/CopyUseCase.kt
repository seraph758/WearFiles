package com.dertefter.menu.usecase

import com.dertefter.data.repository.ClipboardRepository
import javax.inject.Inject

class CopyUseCase @Inject constructor(
    private val clipboardRepository: ClipboardRepository
) {
    operator fun invoke(paths: List<String>) {
        return clipboardRepository.copy(paths)
    }
}