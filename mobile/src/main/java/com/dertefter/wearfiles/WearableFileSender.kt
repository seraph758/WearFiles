package com.dertefter.wearfiles

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import com.dertefter.wearfiles.data.ConnectionStatus
import com.dertefter.wearfiles.data.TransferRepository
import com.dertefter.wearfiles.data.WearNode
import com.google.android.gms.wearable.CapabilityClient
import com.google.android.gms.wearable.Wearable
import kotlinx.coroutines.tasks.await

class WearableFileSender(private val context: Context) {

    
                        suspend fun checkConnection() {
        try {
            val nodeClient = Wearable.getNodeClient(context)
            // 1. 获取物理上真正连着手机的所有蓝牙设备
            val allNodes = nodeClient.connectedNodes.await()

            val newNodes = if (allNodes.isNotEmpty()) {
                // 💡 核心改动：只要有真实的物理手表连着，不管它有没有报告能力，
                // 我们直接拿它真实的 id，并强行赋予 READY 状态！
                allNodes.map { node ->
                    WearNode(node.id, node.displayName, ConnectionStatus.READY)
                }
            } else {
                // 如果物理上真的连一个手表都没有，再显示未连接
                emptyList()
            }

            TransferRepository.updateNodes(newNodes)

        } catch (e: Exception) {
            Log.e("WearableFileSender", e.stackTraceToString())
            TransferRepository.updateNodes(emptyList())
        }
    }


        
            fun sendFileToWear(uri: Uri) {
                val nodeId = TransferRepository.selectedNodeId ?: return
                val fileName = getFileName(uri) ?: context.getString(R.string.file_default_name)
                val intent = Intent(context, FileTransferService::class.java).apply {
                    action = "ADD_TRANSFER"
                    putExtra("file_uri", uri)
                    putExtra("file_name", fileName)
                    putExtra("target_node_id", nodeId)
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
