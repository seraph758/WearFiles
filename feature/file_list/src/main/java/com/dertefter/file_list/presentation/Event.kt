package com.dertefter.file_list.presentation

import java.io.File

sealed class Event {

    data class OnGetFileListAtPath(val path: String? = null) : Event()
    data class OnFileClick(val file: File) : Event()

    data class OnDirectoryClick(val path: String) : Event()

    object OnNavigateBack : Event()

    data class OnNavigateToParent(val path: String) : Event()

    data class OnNavigateToMenu(val path: String) : Event()



}
