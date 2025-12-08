package com.dertefter.design.components.basic_screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material3.CircularProgressIndicator

@Composable
fun ContentLoadingDefaultScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        CircularProgressIndicator(
            modifier = Modifier
                .align(alignment = Alignment.Center),
        )
    }
}

@Composable
@Preview(device = "id:wearos_xl_round", showBackground = true)
fun ContentLoadingDefaultScreenPreview(){
    ContentLoadingDefaultScreen()
}