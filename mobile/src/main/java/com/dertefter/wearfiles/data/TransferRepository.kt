package com.dertefter.wearfiles.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object TransferRepository {
    var queue by mutableStateOf<List<TransferItem>>(emptyList())
        private set

    var availableNodes by mutableStateOf<List<WearNode>>(emptyList())
        private set

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

    fun updateNodes(nodes: List<WearNode>) {
        availableNodes = nodes
        if (!nodes.any { it.id == selectedNodeId }) {
            selectedNodeId = nodes.firstOrNull { it.status == ConnectionStatus.READY }?.id ?: nodes.firstOrNull()?.id
        }
    }
}
