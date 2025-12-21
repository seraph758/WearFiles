package com.dertefter.home.presentation

import com.dertefter.menu.MenuMode

sealed interface MenuState {
    data object Hide : MenuState
    data class Show(
        val path: String,
        val menuMode: MenuMode,
    ) : MenuState

}