package com.dertefter.pdf_viewer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.ScrollIndicator
import com.google.android.horologist.compose.layout.ColumnItemType
import com.google.android.horologist.compose.layout.rememberResponsiveColumnPadding
import dev.zt64.compose.pdf.component.PdfPage
import dev.zt64.compose.pdf.rememberLocalPdfState
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@Composable
fun PdfViewerRoute(
    uriString: String
) {

    val columnState = rememberTransformingLazyColumnState()
    val contentPadding = rememberResponsiveColumnPadding(
        first = ColumnItemType.BodyText,
        last = ColumnItemType.Button,
    )

    val pdfState = rememberLocalPdfState(uri = uriString.toUri())

    var zoomedPageIndex by remember { mutableStateOf<Int?>(null) }

    ScreenScaffold(
        scrollIndicator = {
            if (zoomedPageIndex == null) {
                ScrollIndicator(state = columnState)
            }
        },
        contentPadding = contentPadding,
    ) { contentPadding ->
        TransformingLazyColumn(
            state = columnState,
            contentPadding = contentPadding,
            modifier = Modifier.padding(12.dp),
            userScrollEnabled = zoomedPageIndex == null
        ) {

            items(pdfState.pageCount, key = { it }) { i ->
                val zoomState = rememberZoomState()
                LaunchedEffect(zoomState.scale) {
                    if (zoomState.scale > 1f) {
                        zoomedPageIndex = i
                    } else if (zoomedPageIndex == i) {
                        zoomedPageIndex = null
                    }
                }

                AnimatedVisibility(
                    visible = zoomedPageIndex == null || zoomedPageIndex == i,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    PdfPage(
                        pdfState,
                        index = i,
                        modifier = Modifier
                            .zoomable(zoomState)
                    )
                }
            }
        }
    }

}