package com.dertefter.wearfiles

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import com.google.android.gms.wearable.CapabilityClient
import com.google.android.gms.wearable.Wearable
import kotlinx.coroutines.tasks.await

class WearableFileSender(private val context: Context) {

    suspend fun checkConnection() {
        try {
            val nodeClient = Wearable.getNodeClient(context)
            val capabilityClient = Wearable.getCapabilityClient(context)

            val nodes = nodeClient.connectedNodes.await()
            if (nodes.isEmpty()) {
                TransferState.connectionStatus = ConnectionStatus.NOT_CONNECTED
                return
            }

            val capabilityInfo = capabilityClient
                .getCapability("wear_files_app", CapabilityClient.FILTER_ALL)
                .await()

            if (capabilityInfo.nodes.isEmpty()) {
                TransferState.connectionStatus = ConnectionStatus.APP_NOT_INSTALLED
                return
            }

            val reachableCapabilityInfo = capabilityClient
                .getCapability("wear_files_app", CapabilityClient.FILTER_REACHABLE)
                .await()

            if (reachableCapabilityInfo.nodes.isEmpty()) {
                TransferState.connectionStatus = ConnectionStatus.NOT_NEARBY
                return
            }

            TransferState.connectionStatus = ConnectionStatus.READY

        } catch (e: Exception) {
            Log.e("WearableFileSender", e.stackTraceToString())
            TransferState.connectionStatus = ConnectionStatus.NOT_CONNECTED
        }
    }

    fun sendFileToWear(uri: Uri) {
        val fileName = getFileName(uri) ?: context.getString(R.string.file_default_name)
        val intent = Intent(context, FileTransferService::class.java).apply {
            action = "ADD_TRANSFER"
            putExtra("file_uri", uri)
            putExtra("file_name", fileName)
        }
        context.startForegroundService(intent)
    }

    private fun getFileName(uri: Uri): String? {
        var result: String? = null
        if (uri.scheme == "content") {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            cursor.use { cursor ->
                if (cursor != null && cursor.moveToFirst()) {
                    val index = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    if (index != -1) {
                        result = cursor.getString(index)
                    }
                }
            }
        }
        if (result == null) {
            result = uri.path
            val cut = result?.lastIndexOf('/') ?: -1
            if (cut != -1) {
                result = result?.substring(cut + 1)
            }
        }
        return result
    }
}
