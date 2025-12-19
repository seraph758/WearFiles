package com.dertefter.design.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

object Icons {
    val Wallpaper: ImageVector
        get() {
            if (_Wallpaper != null) {
                return _Wallpaper!!
            }
            _Wallpaper = ImageVector.Builder(
                name = "Wallpaper",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(fill = SolidColor(Color(0xFFE3E3E3))) {
                    moveTo(200f, 840f)
                    quadToRelative(-33f, 0f, -56.5f, -23.5f)
                    reflectiveQuadTo(120f, 760f)
                    verticalLineToRelative(-240f)
                    horizontalLineToRelative(80f)
                    verticalLineToRelative(240f)
                    horizontalLineToRelative(240f)
                    verticalLineToRelative(80f)
                    lineTo(200f, 840f)
                    close()
                    moveTo(520f, 840f)
                    verticalLineToRelative(-80f)
                    horizontalLineToRelative(240f)
                    verticalLineToRelative(-240f)
                    horizontalLineToRelative(80f)
                    verticalLineToRelative(240f)
                    quadToRelative(0f, 33f, -23.5f, 56.5f)
                    reflectiveQuadTo(760f, 840f)
                    lineTo(520f, 840f)
                    close()
                    moveTo(240f, 680f)
                    lineToRelative(120f, -160f)
                    lineToRelative(90f, 120f)
                    lineToRelative(120f, -160f)
                    lineToRelative(150f, 200f)
                    lineTo(240f, 680f)
                    close()
                    moveTo(120f, 440f)
                    verticalLineToRelative(-240f)
                    quadToRelative(0f, -33f, 23.5f, -56.5f)
                    reflectiveQuadTo(200f, 120f)
                    horizontalLineToRelative(240f)
                    verticalLineToRelative(80f)
                    lineTo(200f, 200f)
                    verticalLineToRelative(240f)
                    horizontalLineToRelative(-80f)
                    close()
                    moveTo(760f, 440f)
                    verticalLineToRelative(-240f)
                    lineTo(520f, 200f)
                    verticalLineToRelative(-80f)
                    horizontalLineToRelative(240f)
                    quadToRelative(33f, 0f, 56.5f, 23.5f)
                    reflectiveQuadTo(840f, 200f)
                    verticalLineToRelative(240f)
                    horizontalLineToRelative(-80f)
                    close()
                    moveTo(620f, 400f)
                    quadToRelative(-26f, 0f, -43f, -17f)
                    reflectiveQuadToRelative(-17f, -43f)
                    quadToRelative(0f, -26f, 17f, -43f)
                    reflectiveQuadToRelative(43f, -17f)
                    quadToRelative(26f, 0f, 43f, 17f)
                    reflectiveQuadToRelative(17f, 43f)
                    quadToRelative(0f, 26f, -17f, 43f)
                    reflectiveQuadToRelative(-43f, 17f)
                    close()
                }
            }.build()

            return _Wallpaper!!
        }

    private var _Wallpaper: ImageVector? = null

}