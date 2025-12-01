package com.dertefter.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.wear.compose.material3.ColorScheme
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.dynamicColorScheme

@Composable
fun TheTheme(
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    MaterialTheme(
        colorScheme = dynamicColorScheme(context) ?: ColorScheme(),
        typography = TheTypography,
        content = content
    )
}