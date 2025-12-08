package com.dertefter.menu.usecase

import com.dertefter.data.repository.FileManagerRepository
import com.dertefter.menu.MenuMode
import com.dertefter.menu.presentation.MenuAction
import com.dertefter.menu.presentation.MenuActionType
import javax.inject.Inject

class IsDirectoryUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {
    operator fun invoke(path: String): Boolean {
        return fileManagerRepository.isDirectory(path)
    }
}

