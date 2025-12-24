package com.dertefter.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.wear.compose.material3.ColorScheme
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.dynamicColorScheme
import com.materialkolor.PaletteStyle
import com.materialkolor.rememberDynamicColorScheme

@Composable
fun WearFilesTheme(
    seedColor: Color? = null,
    dynamicColorsEnabled: Boolean = false,
    content: @Composable () -> Unit
) {

    val context = LocalContext.current

    val defaultColor = Color(0xFF832CFF)

    val colorScheme = if (seedColor != null) {
        buildColorScheme(seedColor)
    } else if (dynamicColorsEnabled) {
        dynamicColorScheme(context) ?: buildColorScheme(defaultColor)
    } else {
        buildColorScheme(defaultColor)
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = WearFilesTypography,
        content = content,
    )
}


@Composable
fun buildColorScheme(seedColor: Color): ColorScheme {
    val colorScheme = rememberDynamicColorScheme(
        seedColor = seedColor,
        isDark = true,
        style = PaletteStyle.TonalSpot,
    )

    val wearColorScheme = ColorScheme(
        primary = colorScheme.primary,
        primaryDim = colorScheme.primaryFixedDim,
        primaryContainer = colorScheme.primaryContainer,
        onPrimary = colorScheme.onPrimary,
        onPrimaryContainer = colorScheme.onPrimaryContainer,
        secondary = colorScheme.secondary,
        secondaryDim = colorScheme.secondaryFixedDim,
        secondaryContainer = colorScheme.secondaryContainer,
        onSecondary = colorScheme.onSecondary,
        onSecondaryContainer = colorScheme.onSecondaryContainer,
        tertiary = colorScheme.tertiary,
        tertiaryDim = colorScheme.tertiaryFixedDim,
        tertiaryContainer = colorScheme.tertiaryContainer,
        onTertiary = colorScheme.onTertiary,
        onTertiaryContainer = colorScheme.onTertiaryContainer,
        surfaceContainerLow = colorScheme.surfaceContainerLow,
        surfaceContainer = colorScheme.surfaceContainer,
        surfaceContainerHigh = colorScheme.surfaceContainerHigh,
        onSurface = colorScheme.onSurface,
        onSurfaceVariant = colorScheme.onSurfaceVariant,
        outline = colorScheme.outline,
        outlineVariant = colorScheme.outlineVariant,
        onBackground = colorScheme.onBackground,
        error = colorScheme.error,
        errorDim = colorScheme.error,
        errorContainer = colorScheme.errorContainer,
        onError = colorScheme.onError,
        onErrorContainer = colorScheme.onErrorContainer
    )

    return wearColorScheme
}
