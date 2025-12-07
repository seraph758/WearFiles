package com.dertefter.navigation

import android.net.Uri
import kotlinx.serialization.Serializable
import java.io.File

sealed interface Routes {
    @Serializable
    data object OnBoarding : Routes

    @Serializable
    data class FilesList(
        val path: String? = null
    ) : Routes

    @Serializable
    data class Menu(
        val path: String
    ) : Routes

    @Serializable
    data class TextViewer(val uriString: String) : Routes
}