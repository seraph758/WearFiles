package com.dertefter.wearfiles.data

import android.content.Intent
import androidx.concurrent.futures.await
import androidx.core.net.toUri
import androidx.wear.remote.interactions.RemoteActivityHelper
import com.dertefter.data.repository.RemoteInteractionHandler
import javax.inject.Inject

class RemoteInteractionHandlerImpl @Inject constructor(
    private val helper: RemoteActivityHelper
) : RemoteInteractionHandler {

    override suspend fun openRemoteLink(url: String): Result<Unit> {
        return try {
            helper.startRemoteActivity(
                Intent(Intent.ACTION_VIEW)
                    .addCategory(Intent.CATEGORY_BROWSABLE)
                    .setData(url.toUri())
            ).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}