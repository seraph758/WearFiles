package com.dertefter.music.presentation

import android.net.Uri
import com.dertefter.music.data.MusicItem

sealed class Event {


    object OnLoad : Event()

    object OnNavigateBack : Event()
    data class OnMediaClick(val item: MusicItem) : Event()


}
