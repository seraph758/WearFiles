package com.dertefter.file_list.usecase

import com.dertefter.navigation.Navigator
import com.dertefter.navigation.Routes
import javax.inject.Inject

class NavigateToPathUseCase @Inject constructor(
    private val navigator: Navigator
) {
    operator fun invoke(path: String) {
        return navigator.navigate(Routes.FilesList(path))
    }
}