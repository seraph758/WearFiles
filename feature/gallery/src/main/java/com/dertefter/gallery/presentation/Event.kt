package com.dertefter.gallery.presentation

import com.dertefter.gallery.data.MediaItem

sealed class Event {


    object OnLoad : Event()

    object OnNavigateBack : Event()
    data class OnMediaClick(val item: MediaItem) : Event()


}
