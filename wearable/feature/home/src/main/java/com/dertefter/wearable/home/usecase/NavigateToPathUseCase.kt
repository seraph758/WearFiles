package com.dertefter.wearable.home.usecase

import com.dertefter.wearable.navigation.Navigator
import com.dertefter.wearable.navigation.Routes
import javax.inject.Inject

class NavigateToPathUseCase @Inject constructor(
    private val navigator: Navigator
) {
    operator fun invoke(path: String) {
        return navigator.navigate(Routes.FilesList(path))
    }
}