package com.dertefter.onboarding.presentation.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.Icon

@Composable
fun ContentSuccess(){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "Success",
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
@Preview
fun ContentSuccessPreview(){
    ContentSuccess()
}