package com.dertefter.menu.presentation

import com.dertefter.menu.MenuMode
import java.io.File

sealed class Event {

    object OnNavigateBack : Event()

    data class OnNavigateToRename(val path: String) : Event()

    data class OnNavigateToDelete(val path: String) : Event()

    data class OnHeaderClick(val path: String) : Event()
    data class OnNavigateToNewDirectory(val path: String) : Event()

    data class OnGetMenuActions(val path: String, val mode: MenuMode) : Event()

    data class OnFileClick(val path: String) : Event()

    data class OnPin(val path: String) : Event()

    data class OnUnpin(val path: String) : Event()
    data class OnDirectoryClick(val path: String) : Event()




}
