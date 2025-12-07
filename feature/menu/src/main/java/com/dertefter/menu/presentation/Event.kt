package com.dertefter.menu.presentation

import java.io.File

sealed class Event {

    object OnNavigateBack : Event()

    data class OnGetMenuActions(val path: String) : Event()




}
