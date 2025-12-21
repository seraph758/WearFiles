package com.dertefter.menu.usecase

import com.dertefter.data.model.PinnedItem
import com.dertefter.data.repository.FileManagerRepository
import com.dertefter.data.repository.PinnedRepository
import java.io.File
import java.lang.Exception
import javax.inject.Inject

class UnpinUseCase @Inject constructor(
    private val pinnedRepository: PinnedRepository,
    private val fileManagerRepository: FileManagerRepository
) {
    suspend operator fun invoke(path: String) {

        try {

            val file = fileManagerRepository.getFileByPath(path) ?: return

            val pinnedItem = PinnedItem(
                path = file.absolutePath,
                isFile = file.isFile,
                name = file.name
            )
            pinnedRepository.unpinItem(pinnedItem)

        } catch (e: Exception){

        }


    }
}