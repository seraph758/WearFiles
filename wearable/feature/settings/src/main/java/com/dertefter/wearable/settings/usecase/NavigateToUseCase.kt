package com.dertefter.wearable.settings.usecase

import com.dertefter.wearable.navigation.Navigator
import com.dertefter.wearable.navigation.Routes
import javax.inject.Inject

class NavigateToUseCase @Inject constructor(
    private val navigator: Navigator
) {
    operator fun invoke(routes: Routes) {
        return navigator.navigate(routes)
    }
}