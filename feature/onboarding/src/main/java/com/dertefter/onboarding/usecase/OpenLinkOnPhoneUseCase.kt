package com.dertefter.onboarding.usecase

import com.dertefter.data.repository.RemoteInteractionRepository
import javax.inject.Inject

class OpenLinkOnPhoneUseCase @Inject constructor(
    private val remoteInteractionRepository: RemoteInteractionRepository
) {
    suspend operator fun invoke(url: String) {
        remoteInteractionRepository.openRemoteLink(url)
    }
}
