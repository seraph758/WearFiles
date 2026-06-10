package com.dertefter.wearfiles.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.toShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dertefter.wearfiles.ConnectionStatus
import com.dertefter.wearfiles.R
import com.dertefter.wearfiles.ui.theme.WearFilesTheme

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun NodeConnectionCard(
    modifier: Modifier = Modifier,
    status: ConnectionStatus
) {

    val successColor = colorResource(R.color.success_container)
    val onSuccessColor = colorResource(R.color.on_success_container)

    val warnColor = colorResource(R.color.warn_container)
    val onWarnColor = colorResource(R.color.on_warn_container)

    val containerColor by animateColorAsState(
        if (status == ConnectionStatus.READY) successColor else warnColor
    )

    val contentColor by animateColorAsState(
        if (status == ConnectionStatus.READY) onSuccessColor else onWarnColor
    )

    val infiniteTransition = rememberInfiniteTransition(label = "rotation")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(24000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "angle"
    )

    val startAngle = if (status == ConnectionStatus.READY) angle.toInt() else 0

    val iconRes = if (status == ConnectionStatus.READY){
        R.drawable.ic_watch_check
    } else {
        R.drawable.ic_watch_off
    }


    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(24.dp),
        color = containerColor,
        contentColor = contentColor
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = containerColor,
                modifier = Modifier
                    .clip(MaterialShapes.Cookie12Sided.toShape(startAngle = startAngle))
                    .background(contentColor)
                    .padding(8.dp)
                    .size(32.dp)
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(
                    text = when(status) {
                        ConnectionStatus.READY -> stringResource(R.string.watch_connected)
                        ConnectionStatus.NOT_CONNECTED -> stringResource(R.string.watch_not_found)
                        ConnectionStatus.NOT_NEARBY -> stringResource(R.string.watch_not_nearby)
                        ConnectionStatus.APP_NOT_INSTALLED -> stringResource(R.string.app_not_installed)
                    },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = when(status) {
                        ConnectionStatus.READY -> stringResource(R.string.ready_to_send)
                        ConnectionStatus.NOT_CONNECTED -> stringResource(R.string.check_connection)
                        ConnectionStatus.NOT_NEARBY -> stringResource(R.string.bring_watch_closer)
                        ConnectionStatus.APP_NOT_INSTALLED -> stringResource(R.string.install_app_on_watch)
                    },
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NodeConnectionCardPreview_Connected() {
    WearFilesTheme {
        NodeConnectionCard(status = ConnectionStatus.READY)
    }
}

@Preview(showBackground = true)
@Composable
fun NodeConnectionCardPreview_Disconnected() {
    WearFilesTheme {
        NodeConnectionCard(status = ConnectionStatus.NOT_CONNECTED)
    }
}
