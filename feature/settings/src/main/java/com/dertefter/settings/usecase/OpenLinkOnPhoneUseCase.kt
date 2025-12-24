package com.dertefter.settings.usecase

import com.dertefter.data.repository.RemoteInteractionHandler
import javax.inject.Inject

class OpenLinkOnPhoneUseCase @Inject constructor(
    private val remoteInteractionHandler: RemoteInteractionHandler
) {
    suspend operator fun invoke(url: String): Result<Unit> {
        return remoteInteractionHandler.openRemoteLink(url)
    }
}
