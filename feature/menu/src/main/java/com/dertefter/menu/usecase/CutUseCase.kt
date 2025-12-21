package com.dertefter.menu.usecase

import com.dertefter.data.repository.ClipboardRepository
import javax.inject.Inject

class CutUseCase @Inject constructor(
    private val clipboardRepository: ClipboardRepository
) {
    operator fun invoke(path: String) {
        return clipboardRepository.cut(path)
    }
}