package com.dertefter.menu.usecase

import com.dertefter.navigation.Navigator
import com.dertefter.navigation.Routes
import javax.inject.Inject

class NavigateToDeleteUseCase @Inject constructor(
    private val navigator: Navigator
) {
    operator fun invoke(paths: List<String>) {
        navigator.navigate(Routes.Delete(paths))
    }
}