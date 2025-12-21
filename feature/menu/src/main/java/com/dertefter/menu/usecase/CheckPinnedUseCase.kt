package com.dertefter.menu.usecase

import com.dertefter.data.model.PinnedItem
import com.dertefter.data.repository.PinnedRepository
import java.io.File
import javax.inject.Inject

class CheckPinnedUseCase @Inject constructor(
    private val pinnedRepository: PinnedRepository
) {
    suspend operator fun invoke(path: String): Boolean {
        return pinnedRepository.isPinned(path)
    }
}