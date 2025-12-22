package com.dertefter.music.usecase

import com.dertefter.data.repository.FileManagerRepository
import javax.inject.Inject

class CheckPermissionsUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {

    operator fun invoke(): Boolean {
        return fileManagerRepository.hasAudioAccess()
    }

}
