package com.dertefter.file_list.usecase

import com.dertefter.data.repository.FileManagerRepository
import com.dertefter.file_list.presentation.MoreAction
import javax.inject.Inject

class GetMoreActionsUseCase @Inject constructor(

    private val fileManagerRepository: FileManagerRepository
) {
    operator fun invoke(path: String): List<MoreAction> {
        val actions = mutableListOf<MoreAction>()

        val canRename = fileManagerRepository.canRename(path)

        if (canRename){
            actions.add(MoreAction.RENAME)
        }

        return actions

    }
}