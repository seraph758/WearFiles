package com.dertefter.wearfiles.presentation

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dertefter.wearfiles.ConnectionStatus
import com.dertefter.wearfiles.FileTransferService
import com.dertefter.wearfiles.R
import com.dertefter.wearfiles.TransferItem
import com.dertefter.wearfiles.TransferState
import com.dertefter.wearfiles.TransferStatus
import com.dertefter.wearfiles.WearableFileSender
import com.dertefter.wearfiles.ui.theme.WearFilesTheme

@Composable
fun FilePickerScreen(
    modifier: Modifier = Modifier,
    initialUris: List<Uri> = emptyList(),
    contentPadding: PaddingValues = PaddingValues(),
) {
    val context = LocalContext.current
    val fileSender = remember { WearableFileSender(context) }
    
    val queue = TransferState.queue.filter { it.targetNodeId == TransferState.selectedNodeId }

    LaunchedEffect(initialUris) {
        if (initialUris.isNotEmpty()) {
            initialUris.forEach { uri ->
                fileSender.sendFileToWear(uri)
            }
        }
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris: List<Uri> ->
        uris.forEach { uri ->
            fileSender.sendFileToWear(uri)
        }
    }

    FilePickerContent(
        modifier = modifier
            .padding(top = contentPadding.calculateTopPadding()),
        queue = queue,
        onSelectFiles = { launcher.launch("*/*") },
        onCancelTransfer = { item ->
            val intent = Intent(context, FileTransferService::class.java).apply {
                action = "CANCEL_TRANSFER"
                putExtra("item_id", item.id)
            }
            context.startService(intent)
        },
        contentPadding = contentPadding
    )
}

@Composable
fun FilePickerContent(
    modifier: Modifier = Modifier,
    queue: List<TransferItem> = emptyList(),
    onSelectFiles: () -> Unit = {},
    onCancelTransfer: (TransferItem) -> Unit = {},
    contentPadding: PaddingValues = PaddingValues(),
) {
    Column(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = 32.dp,
                    topEnd = 32.dp
                )
            )
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 14.dp)
            .padding(top = 14.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Button(
            onClick = onSelectFiles,
            modifier = Modifier.fillMaxWidth(),
            enabled = TransferState.connectionStatus == ConnectionStatus.READY
        ) {
            Text(stringResource(R.string.select_files))
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (queue.isEmpty()) {
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Text(stringResource(R.string.queue_empty), color = Color.Gray)
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(
                    start = contentPadding.calculateStartPadding(LocalLayoutDirection.current),
                    top = 0.dp,
                    end = contentPadding.calculateEndPadding(LocalLayoutDirection.current),
                    bottom = contentPadding.calculateBottomPadding()
                ),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {

                item {
                    Text(
                        stringResource(R.string.transfer_queue_title),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight(600),
                        modifier = Modifier.padding(
                            horizontal = 24.dp,
                            vertical = 4.dp
                        )
                    )
                }

                itemsIndexed(queue, key = { _, item -> item.id }) { index, item ->
                    TransferItem(
                        item = item,
                        onCancel = {
                            onCancelTransfer(item)
                        },
                        isFirst = index == 0,
                        isLast = index == queue.lastIndex
                    )

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFilePickerScreen() {
    WearFilesTheme {
        FilePickerContent(
            queue = listOf(
                TransferItem(
                    id = "1",
                    targetNodeId = "node1",
                    uri = Uri.EMPTY,
                    fileName = "test_file.txt",
                    status = TransferStatus.PENDING
                ),
                TransferItem(
                    id = "2",
                    targetNodeId = "node1",
                    uri = Uri.EMPTY,
                    fileName = "image.png",
                    progress = 50,
                    status = TransferStatus.SENDING
                ),
                TransferItem(
                    id = "3",
                    targetNodeId = "node1",
                    uri = Uri.EMPTY,
                    fileName = "video.mp4",
                    status = TransferStatus.SUCCESS
                ),
                TransferItem(
                    id = "4",
                    targetNodeId = "node1",
                    uri = Uri.EMPTY,
                    fileName = "large_file.zip",
                    status = TransferStatus.ERROR
                )
            )
        )
    }
}
