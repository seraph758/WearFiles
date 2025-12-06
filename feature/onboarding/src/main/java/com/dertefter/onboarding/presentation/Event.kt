package com.dertefter.onboarding.presentation

sealed class Event {

    object OnCheckPermissions : Event()

    object OnOpenLinkOnPhone : Event()

    object OnNavigateToFileList : Event()

    object CloseDialog : Event()

    data class ShowDialog (val isSuccessful: Boolean)  : Event()

}