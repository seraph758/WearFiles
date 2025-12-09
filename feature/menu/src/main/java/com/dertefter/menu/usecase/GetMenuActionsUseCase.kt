package com.dertefter.menu.usecase

import com.dertefter.data.repository.FileManagerRepository
import com.dertefter.menu.MenuMode
import com.dertefter.menu.presentation.MenuAction
import com.dertefter.menu.presentation.MenuActionType
import javax.inject.Inject

class GetMenuActionsUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {
    operator fun invoke(path: String, mode: MenuMode): List<MenuAction> {

        when (mode) {
            MenuMode.INSIDE -> {
                return buildList {
                    if (fileManagerRepository.canCreateDirHere(path)) {
                        add(MenuAction(MenuActionType.NEW_DIR, path))
                    }
                }
            }
            MenuMode.OUTSIDE -> {
                return buildList {
                    add(MenuAction(MenuActionType.OPEN, path))

                    if (fileManagerRepository.canBeRenamed(path)) {
                        add(MenuAction(MenuActionType.RENAME, path))
                    }

                    if (fileManagerRepository.canBeDeleted(path)) {
                        add(MenuAction(MenuActionType.DELETE, path))
                    }
                }
            }
        }

    }
}

