package com.dertefter.home.presentation

import com.dertefter.navigation.Routes

sealed class Event {

    data class OnNavigateTo(val routes: Routes) : Event()




}
