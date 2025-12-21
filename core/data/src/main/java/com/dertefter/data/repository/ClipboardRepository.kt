package com.dertefter.data.repository

interface ClipboardRepository {

    fun cut(path: String)

    fun copy(path: String)

    fun cancel()

    suspend fun insertTo(path: String): Boolean

    var operation: ClipboardOperation?


}

enum class ClipboardOperation { CUT, COPY }