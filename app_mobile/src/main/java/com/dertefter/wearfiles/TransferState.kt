package com.dertefter.wearfiles

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class TransferStatus {
    PENDING,
    SENDING,
    SUCCESS,
    ERROR
}

data class TransferItem(
    val id: String,
    val targetNodeId: String,
    val uri: Uri,
    val fileName: String,
    val progress: Int = 0,
    val status: TransferStatus = TransferStatus.PENDING
)


enum class ConnectionStatus {
    NOT_CONNECTED,
    NOT_NEARBY,
    APP_NOT_INSTALLED,
    READY,
}


data class WearNode(
    val id: String,
    val name: String,
    val status: ConnectionStatus
)

object TransferState {
    var queue by mutableStateOf<List<TransferItem>>(emptyList())
    var availableNodes by mutableStateOf<List<WearNode>>(emptyList())
    var selectedNodeId by mutableStateOf<String?>(null)

    val connectionStatus: ConnectionStatus
        get() = availableNodes.find { it.id == selectedNodeId }?.status ?: ConnectionStatus.NOT_CONNECTED

    fun addItem(item: TransferItem) {
        queue = queue + item
    }

    fun removeItem(id: String) {
        queue = queue.filter { it.id != id }
    }

    fun updateItem(id: String, update: (TransferItem) -> TransferItem) {
        queue = queue.map { if (it.id == id) update(it) else it }
    }
}
