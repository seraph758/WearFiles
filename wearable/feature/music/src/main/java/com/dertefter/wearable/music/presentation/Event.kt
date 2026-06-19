package com.dertefter.wearable.music.presentation

import com.dertefter.wearable.music.data.MusicItem

sealed class Event {


    object OnLoad : Event()

    object OnNavigateBack : Event()
    data class OnMediaClick(val item: MusicItem) : Event()


}
