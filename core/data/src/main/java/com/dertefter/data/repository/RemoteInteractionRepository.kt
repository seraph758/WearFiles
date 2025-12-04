package com.dertefter.data.repository

interface RemoteInteractionRepository {
    suspend fun openRemoteLink(url: String)
}
