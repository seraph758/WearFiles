package com.dertefter.menu.usecase

import com.dertefter.data.repository.FileManagerRepository
import com.dertefter.menu.MenuMode
import com.dertefter.menu.presentation.MenuAction
import com.dertefter.menu.presentation.MenuActionType
import javax.inject.Inject

class GetMenuActionsUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository,
    private val checkPinnedUseCase: CheckPinnedUseCase
) {
    suspend operator fun invoke(path: String, mode: MenuMode): List<MenuAction> {


        val menuList = when (mode) {
            MenuMode.INSIDE -> {
                buildList {
                    if (fileManagerRepository.canCreateDirHere(path)) {
                        add(MenuAction(MenuActionType.NEW_DIR, path))
                    }
                }
            }

            MenuMode.OUTSIDE -> {
                buildList {
                    add(MenuAction(MenuActionType.OPEN, path))

                    if (fileManagerRepository.canBeRenamed(path)) {
                        add(MenuAction(MenuActionType.RENAME, path))
                    }

                    if (fileManagerRepository.canBeDeleted(path)) {
                        add(MenuAction(MenuActionType.DELETE, path))
                    }
                }
            }

            MenuMode.PINNED -> {
                buildList {
                    add(MenuAction(MenuActionType.OPEN, path))
                }
            }
        }.toMutableList()

        val isPinned = checkPinnedUseCase(path)

        if (isPinned) {
            menuList.add(
                MenuAction(MenuActionType.UNPIN, path)
            )
        } else {
            menuList.add(
                MenuAction(MenuActionType.PIN, path)
            )
        }

        return menuList

    }
}

