package com.dertefter.menu.usecase

import com.dertefter.data.repository.FileManagerRepository
import com.dertefter.menu.presentation.MenuAction
import com.dertefter.menu.presentation.MenuActionType
import javax.inject.Inject

class GetMenuActionsUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {
    operator fun invoke(path: String): List<MenuAction> =
        listOfNotNull(
            MenuAction(MenuActionType.RENAME, path).takeIf {
                fileManagerRepository.canBeRenamed(path)
            },

            MenuAction(MenuActionType.NEW_DIR, path).takeIf {
                fileManagerRepository.canCreateDirHere(path)
            },

            MenuAction(MenuActionType.DELETE, path).takeIf {
                fileManagerRepository.canBeDeleted(path)
            },

        )
}
