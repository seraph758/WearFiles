package com.dertefter.wearfiles

import com.dertefter.wearfiles.data.TransferRepository
import com.dertefter.wearfiles.data.TransferStatus
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService

class TransferStatusListener : WearableListenerService() {

    override fun onMessageReceived(messageEvent: MessageEvent) {
        if (messageEvent.path == "/file-transfer-status") {
            val message = String(messageEvent.data)
            val parts = message.split(":", limit = 2)
            if (parts.size == 2) {
                val status = parts[0]
                val fileName = parts[1]
                val sourceNodeId = messageEvent.sourceNodeId
                
                val notificationHelper = NotificationHelper(this)
                if (status == "success") {
                    TransferRepository.queue.find { it.fileName == fileName && it.targetNodeId == sourceNodeId && it.status == TransferStatus.SENDING }?.let { item ->
                        TransferRepository.updateItem(item.id) { it.copy(status = TransferStatus.SUCCESS, progress = 100) }
                    }
                    notificationHelper.showTransferNotification(fileName, TransferStatus.SUCCESS)
                } else {
                    TransferRepository.queue.find { it.fileName == fileName && it.targetNodeId == sourceNodeId && it.status == TransferStatus.SENDING }?.let { item ->
                        TransferRepository.updateItem(item.id) { it.copy(status = TransferStatus.ERROR) }
                    }
                    notificationHelper.showTransferNotification(fileName, TransferStatus.ERROR)
                }
            }
        }
    }
}
