package com.dertefter.menu.presentation.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialShapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.Text
import com.dertefter.menu.R

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun OperationWorking(){
    Box(
        modifier = Modifier.fillMaxSize()
    ){

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth().align(alignment = Alignment.Center)
        ) {
            LoadingIndicator(
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(64.dp),
                polygons = listOf(
                    MaterialShapes.Cookie4Sided,
                    MaterialShapes.SoftBoom,
                    MaterialShapes.Cookie9Sided,
                    MaterialShapes.Clover4Leaf,
                )

            )

            Text(stringResource(R.string.wait),
                style = MaterialTheme.typography.titleSmall)
        }

    }
}