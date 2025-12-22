package com.dertefter.home.usecase

import com.dertefter.navigation.Navigator
import com.dertefter.navigation.Routes
import javax.inject.Inject

class NavigateToMusicUseCase @Inject constructor(
    private val navigator: Navigator
) {
    operator fun invoke() {
        return navigator.navigate(Routes.Music)
    }
}