package com.dertefter.wearable.home.presentation

import com.dertefter.wearable.menu.MenuMode

sealed interface MenuState {
    data object Hide : MenuState
    data class Show(
        val paths: List<String>,
        val menuMode: MenuMode,
    ) : MenuState

}