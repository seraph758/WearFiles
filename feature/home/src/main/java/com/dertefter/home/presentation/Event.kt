package com.dertefter.home.presentation

import com.dertefter.menu.MenuMode
import java.io.File

sealed class Event {

    object OnNavigateToGallery : Event()

    object OnNavigateToStorage : Event()

    object OnHideMenu : Event()

    data class OnShowMenuFor(val path: String, val menuMode: MenuMode = MenuMode.PINNED) : Event()


    data class OnFileClick(val path: String) : Event()

    data class OnDirectoryClick(val path: String) : Event()

}
