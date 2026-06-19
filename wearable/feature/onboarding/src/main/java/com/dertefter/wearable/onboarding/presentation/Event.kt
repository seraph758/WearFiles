package com.dertefter.wearable.onboarding.presentation

sealed class Event {

    object OnOpenLinkOnPhone : Event()

    object CloseDialog : Event()

    data class ShowDialog (val isSuccessful: Boolean)  : Event()

}