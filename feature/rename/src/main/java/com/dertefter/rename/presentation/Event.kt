package com.dertefter.rename.presentation

sealed class Event {

    data class OnGetFileName(val path: String) : Event()
    data class OnNewFileNameChanged(val name: String) : Event()

}
