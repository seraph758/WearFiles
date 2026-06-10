package com.dertefter.wearfiles.presentation

import android.net.Uri
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dertefter.wearfiles.R
import com.dertefter.wearfiles.TransferItem
import com.dertefter.wearfiles.TransferState
import com.dertefter.wearfiles.TransferStatus
import com.dertefter.wearfiles.ui.theme.WearFilesTheme

@Composable
fun TransferItem(
    modifier: Modifier = Modifier,
    item: TransferItem,
    onCancel: () -> Unit,
    isFirst: Boolean = false,
    isLast: Boolean = false,
) {

    val largeRounding = 24.dp
    val smallRounding = 8.dp

    val topRounding = if (isFirst) largeRounding else smallRounding
    val bottomRounding = if (isLast) largeRounding else smallRounding

    val shape = RoundedCornerShape(
        topEnd =topRounding,
        topStart = topRounding,
        bottomEnd = bottomRounding,
        bottomStart = bottomRounding
    )

    val successColor = colorResource(R.color.success_container)
    val errorColor = MaterialTheme.colorScheme.errorContainer
    val defaultColor = MaterialTheme.colorScheme.surfaceContainerLowest

    val onSuccessColor = colorResource(R.color.on_success_container)
    val onErrorColor = MaterialTheme.colorScheme.onErrorContainer
    val onDefaultColor = MaterialTheme.colorScheme.onSurface

    val bgColor by animateColorAsState(
        when (item.status){
            TransferStatus.PENDING -> defaultColor
            TransferStatus.SENDING -> defaultColor
            TransferStatus.SUCCESS -> successColor
            TransferStatus.ERROR -> errorColor
        }
    )

    val contentColor by animateColorAsState(
        when (item.status){
            TransferStatus.PENDING -> onDefaultColor
            TransferStatus.SENDING -> onDefaultColor
            TransferStatus.SUCCESS -> onSuccessColor
            TransferStatus.ERROR -> onErrorColor
        }
    )

    val iconRes = R.drawable.ic_close

    val onButtonClick = {
        if (item.status == TransferStatus.PENDING || item.status == TransferStatus.SENDING) {
            onCancel()
        } else {
            TransferState.removeItem(item.id)
        }
    }

    Column(modifier = modifier
        .clip(shape)
        .background(MaterialTheme.colorScheme.surfaceContainerLowest)
        .background(
            brush = Brush.linearGradient(
                colors = listOf(
                    bgColor.copy(alpha = 0.02f),
                    bgColor.copy(alpha = 0.3f)
                )
            )
        )
        .fillMaxWidth()) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.fileName,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Text(
                    text = when (item.status) {
                        TransferStatus.PENDING -> stringResource(R.string.status_pending)
                        TransferStatus.SENDING -> if (item.progress < 100) stringResource(R.string.status_sending) else stringResource(R.string.status_waiting_watch)
                        TransferStatus.SUCCESS -> stringResource(R.string.status_success)
                        TransferStatus.ERROR -> stringResource(R.string.status_error)
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = when (item.status) {
                        TransferStatus.SUCCESS -> Color(0xFF4CAF50)
                        TransferStatus.ERROR -> Color.Red
                        else -> Color.Gray
                    }
                )
            }




            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable(onClick = onButtonClick)
                    .background(contentColor)
                    .padding(4.dp)
                    .size(44.dp),
                contentAlignment = Alignment.Center
            ){
                if (item.status == TransferStatus.SENDING){
                    CircularProgressIndicator(
                        progress = {item.progress / 100f },
                        modifier = Modifier
                            .size(40.dp),
                        color = bgColor,
                        trackColor = bgColor.copy(alpha = 0.4f)
                    )
                }

                Icon(
                    painter = painterResource(iconRes),
                    tint = bgColor,
                    contentDescription = stringResource(R.string.action_remove)
                )
            }


        }

    }

}

@Preview(showBackground = false)
@Composable
fun PreviewTransferItem() {
    WearFilesTheme {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            TransferItem(
                item = TransferItem(
                    id = "1",
                    uri = Uri.EMPTY,
                    fileName = "test_file.txt",
                    status = TransferStatus.PENDING
                ),
                isFirst = true,
                onCancel = {}
            )
            TransferItem(
                item = TransferItem(
                    id = "2",
                    uri = Uri.EMPTY,
                    fileName = "image.png",
                    progress = 50,
                    status = TransferStatus.SENDING
                ),
                onCancel = {}
            )
            TransferItem(
                item = TransferItem(
                    id = "3",
                    uri = Uri.EMPTY,
                    fileName = "video.mp4",
                    status = TransferStatus.SUCCESS
                ),
                onCancel = {}
            )
            TransferItem(
                item = TransferItem(
                    id = "4",
                    uri = Uri.EMPTY,
                    fileName = "large_file.zip",
                    status = TransferStatus.ERROR
                ),
                onCancel = {}
            )
        }
    }
}
