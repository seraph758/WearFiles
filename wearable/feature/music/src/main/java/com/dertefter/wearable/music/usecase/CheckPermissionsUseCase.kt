package com.dertefter.wearable.music.usecase

import com.dertefter.wearable.data.repository.FileManagerRepository
import javax.inject.Inject

class CheckPermissionsUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {

    operator fun invoke(): Boolean {
        return fileManagerRepository.hasAudioAccess()
    }

}
