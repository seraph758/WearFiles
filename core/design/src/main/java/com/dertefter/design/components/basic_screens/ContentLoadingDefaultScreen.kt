package com.dertefter.design.components.basic_screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialShapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.CircularProgressIndicator
import androidx.wear.compose.material3.MaterialTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ContentLoadingDefaultScreen() {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(2000)
        visible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            modifier = Modifier
                .align(alignment = Alignment.Center)
        ) {
            LoadingIndicator(
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .size(72.dp),
                polygons = listOf(
                    MaterialShapes.Cookie4Sided,
                    MaterialShapes.SoftBoom,
                    MaterialShapes.Cookie9Sided,
                    MaterialShapes.Clover4Leaf,
                )

            )
        }
    }
}

@Composable
@Preview(device = "id:wearos_xl_round", showBackground = true)
fun ContentLoadingDefaultScreenPreview() {
    ContentLoadingDefaultScreen()
}