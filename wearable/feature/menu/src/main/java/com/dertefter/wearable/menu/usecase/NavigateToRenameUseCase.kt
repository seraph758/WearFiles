package com.dertefter.wearable.menu.usecase

import com.dertefter.wearable.navigation.Navigator
import com.dertefter.wearable.navigation.Routes
import javax.inject.Inject

class NavigateToRenameUseCase @Inject constructor(
    private val navigator: Navigator
) {
    operator fun invoke(path: String) {
        navigator.navigate(Routes.Rename(path))
    }
}