package com.dertefter.wearfiles.data

import android.net.Uri

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
