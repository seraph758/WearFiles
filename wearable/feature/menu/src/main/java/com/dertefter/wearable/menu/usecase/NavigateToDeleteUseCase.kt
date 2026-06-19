package com.dertefter.wearable.menu.usecase

import com.dertefter.wearable.navigation.Navigator
import com.dertefter.wearable.navigation.Routes
import javax.inject.Inject

class NavigateToDeleteUseCase @Inject constructor(
    private val navigator: Navigator
) {
    operator fun invoke(paths: List<String>) {
        navigator.navigate(Routes.Delete(paths))
    }
}