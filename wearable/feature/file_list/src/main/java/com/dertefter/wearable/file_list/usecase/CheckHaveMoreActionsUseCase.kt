package com.dertefter.wearable.file_list.usecase

import com.dertefter.wearable.data.repository.FileManagerRepository
import javax.inject.Inject

class CheckHaveMoreActionsUseCase @Inject constructor(

    private val fileManagerRepository: FileManagerRepository
) {
    suspend operator fun invoke(path: String): Boolean {

        val canRename = fileManagerRepository.canBeRenamed(path)
        val canBeDeleted = fileManagerRepository.canBeDeleted(path)
        val canCreateDirHere = fileManagerRepository.canCreateDirHere(path)

        return canRename || canBeDeleted || canCreateDirHere

    }
}