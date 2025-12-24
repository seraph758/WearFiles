package com.dertefter.home.presentation

import com.dertefter.menu.MenuMode
import com.dertefter.navigation.Routes

sealed class Event {

    data class OnNavigateTo (val routes: Routes) : Event()

    object OnNavigateToStorage : Event()

    object OnHideMenu : Event()

    data class OnShowMenuFor(val path: String, val menuMode: MenuMode = MenuMode.PINNED) : Event()


    data class OnFileClick(val path: String) : Event()

    data class OnDirectoryClick(val path: String) : Event()

}
