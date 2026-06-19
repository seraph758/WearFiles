package com.dertefter.menu.usecase

import com.dertefter.data.repository.PinnedRepository
import javax.inject.Inject

class CheckAllPinnedUseCase @Inject constructor(
    private val pinnedRepository: PinnedRepository
) {
    suspend operator fun invoke(paths: List<String>): Boolean =
        paths.all { pinnedRepository.isPinned(it) }
}