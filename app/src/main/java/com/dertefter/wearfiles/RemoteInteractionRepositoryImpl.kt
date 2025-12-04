package com.dertefter.wearfiles

import android.content.Intent
import androidx.concurrent.futures.await
import androidx.core.net.toUri
import androidx.wear.remote.interactions.RemoteActivityHelper
import com.dertefter.data.repository.RemoteInteractionRepository
import javax.inject.Inject

class RemoteInteractionRepositoryImpl @Inject constructor(
    private val helper: RemoteActivityHelper
) : RemoteInteractionRepository {

    override suspend fun openRemoteLink(url: String) {
        helper.startRemoteActivity(
            Intent(Intent.ACTION_VIEW)
                .addCategory(Intent.CATEGORY_BROWSABLE)
                .setData(url.toUri())
        ).await()
    }
}