package com.dertefter.wearfiles.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dertefter.wearfiles.ConnectionStatus
import com.dertefter.wearfiles.R
import com.dertefter.wearfiles.ui.theme.WearFilesTheme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.LaunchedEffect
import com.dertefter.wearfiles.WearNode

@Composable
fun NodeSelectionPager(
    modifier: Modifier = Modifier,
    nodes: List<WearNode>,
    selectedNodeId: String?,
    onNodeSelected: (String) -> Unit
) {
    if (nodes.isEmpty()) {
        NodeConnectionCard(
            modifier = modifier,
            name = stringResource(R.string.watch_not_found),
            status = ConnectionStatus.NOT_CONNECTED
        )
        return
    }

    val initialPage = nodes.indexOfFirst { it.id == selectedNodeId }.coerceAtLeast(0)
    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { nodes.size }
    )

    LaunchedEffect(pagerState.currentPage) {
        onNodeSelected(nodes[pagerState.currentPage].id)
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxWidth()
    ) { page ->
        val node = nodes[page]
        NodeConnectionCard(
            name = node.name,
            status = node.status
        )
    }
}

@Composable
fun NodeConnectionCard(
    modifier: Modifier = Modifier,
    name: String,
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

    val startAngle = if (status == ConnectionStatus.READY) angle else 0f

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

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(54.dp)

            ){
                Icon(
                    painter = painterResource(R.drawable.some_shape),
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.rotate(startAngle)
                )

                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    tint = containerColor,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize()
                )
            }

            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {

                Text(
                    text = name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )

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

        Column(
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            NodeConnectionCard(status = ConnectionStatus.READY, name = "TEST")
        }


    }
}
