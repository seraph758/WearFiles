package com.dertefter.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable
    data object OnBoarding : Routes

    @Serializable
    data class FilesList(
        val path: String? = null
    ) : Routes
}