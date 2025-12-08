package com.dertefter.menu.usecase

import com.dertefter.navigation.Navigator
import com.dertefter.navigation.Routes
import javax.inject.Inject

class NavigateToNewDirectoryUseCase @Inject constructor(
    private val navigator: Navigator
) {
    operator fun invoke(path: String) {
        navigator.navigate(Routes.NewDirectory(path))
    }
}