package com.dertefter.data.model

data class PrettyPath(
    val homePath: String,
    val path: String,
) {
    fun getPrettyPath(): String? {

        return try {
            if (!path.contains(homePath)) {
                return null
            }
            path.replace(homePath, "")
        } catch (e: Exception) {
            null
        }

    }
}
