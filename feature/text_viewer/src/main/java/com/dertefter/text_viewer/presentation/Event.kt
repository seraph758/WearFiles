package com.dertefter.text_viewer.presentation

import java.io.File

sealed class Event {

    data class OnGetFileTextContent(val uriString: String) : Event()

    object OnExit : Event()

}