package com.dertefter.menu.usecase

import com.dertefter.data.repository.ClipboardRepository
import com.dertefter.data.repository.FileManagerRepository
import com.dertefter.menu.MenuMode
import com.dertefter.menu.presentation.MenuAction
import com.dertefter.menu.presentation.MenuActionType
import javax.inject.Inject

class GetMenuActionsUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository,
    private val clipboardRepository: ClipboardRepository,
    private val checkPinnedUseCase: CheckPinnedUseCase,
) {
    suspend operator fun invoke(path: String, mode: MenuMode): List<MenuAction> {

        val isPinned = checkPinnedUseCase(path)

        val menuList = when (mode) {
            MenuMode.INSIDE -> {
                buildList {
                    if (fileManagerRepository.canCreateDirHere(path)) {
                        add(MenuAction(MenuActionType.NEW_DIR, path))
                    }

                    if (clipboardRepository.operation != null) {
                        add(MenuAction(MenuActionType.PASTE, path))
                    }

                }
            }

            MenuMode.OUTSIDE -> {
                buildList {
                    add(MenuAction(MenuActionType.OPEN, path))

                    if (fileManagerRepository.canBeRenamed(path)) {
                        add(MenuAction(MenuActionType.RENAME, path))
                    }

                    if (clipboardRepository.operation == null) {
                        add(MenuAction(MenuActionType.CUT, path))
                        add(MenuAction(MenuActionType.COPY, path))
                    } else {
                        add(MenuAction(MenuActionType.CANCEL_PASTE, path))
                    }

                    if (isPinned){
                        add(MenuAction(MenuActionType.UNPIN, path))
                    }else{
                        add(MenuAction(MenuActionType.PIN, path))
                    }

                    if (fileManagerRepository.canBeDeleted(path)) {
                        add(MenuAction(MenuActionType.DELETE, path))
                    }
                }
            }

            MenuMode.PINNED -> {
                buildList {
                    add(MenuAction(MenuActionType.OPEN, path))

                    if (isPinned){
                        add(MenuAction(MenuActionType.UNPIN, path))
                    }else{
                        add(MenuAction(MenuActionType.PIN, path))
                    }

                }
            }
        }.toMutableList()



        return menuList

    }
}

