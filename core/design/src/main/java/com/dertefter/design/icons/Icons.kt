package com.dertefter.design.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.dertefter.design.R

object Icons {

    val Watch: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_watch)

    val Wallpaper: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_wallpaper)
}