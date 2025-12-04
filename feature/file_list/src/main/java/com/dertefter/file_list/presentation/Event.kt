package com.dertefter.file_list.presentation

import java.io.File

sealed class Event {

    data class OnGetFileListAtPath(val path: String? = null) : Event()
    data class OnFileClick(val file: File) : Event()

    data class OnDirectoryClick(val file: File) : Event()


}
