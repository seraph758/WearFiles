package com.dertefter.home.presentation

import com.dertefter.home.data.model.HomeItem
import com.dertefter.menu.MenuMode
import java.io.File

sealed class Event {

    data class OnNavigateTo (val item: HomeItem) : Event()

    object OnNavigateToStorage : Event()

    object OnHideMenu : Event()

    data class OnShowMenuFor(val path: String, val menuMode: MenuMode = MenuMode.PINNED) : Event()


    data class OnFileClick(val path: String) : Event()

    data class OnDirectoryClick(val path: String) : Event()

}
