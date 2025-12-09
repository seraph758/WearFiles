package com.dertefter.delete.presentation

sealed class Event {

    object OnNavigateBack : Event()
    data class OnDelete(val path: String) : Event()

}
