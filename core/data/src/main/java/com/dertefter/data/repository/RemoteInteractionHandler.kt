package com.dertefter.data.repository

interface RemoteInteractionHandler {
    suspend fun openRemoteLink(url: String): Result<Unit>
}
