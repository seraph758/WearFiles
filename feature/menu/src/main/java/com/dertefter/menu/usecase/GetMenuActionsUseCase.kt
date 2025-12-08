package com.dertefter.menu.usecase

import com.dertefter.data.repository.FileManagerRepository
import com.dertefter.menu.presentation.MenuAction
import com.dertefter.menu.presentation.MenuActionType
import javax.inject.Inject

class GetMenuActionsUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {
    operator fun invoke(path: String): List<MenuAction> {
        val actions = mutableListOf<MenuAction>()

        val canRename = fileManagerRepository.canRename(path)

        if (canRename){
            actions.add(MenuAction(MenuActionType.RENAME, path = path))
        }

        return actions

    }
}