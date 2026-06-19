package com.dertefter.wearable.file_list.presentation.content

import com.dertefter.wearable.menu.MenuMode

sealed interface MenuState {
    data object Hide : MenuState
    data class Show(
        val paths: List<String>,
        val menuMode: MenuMode,
    ) : MenuState

}