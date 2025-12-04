package com.dertefter.onboarding.usecase

import com.dertefter.data.repository.FileManagerRepository
import com.dertefter.navigation.Navigator
import com.dertefter.navigation.Routes
import javax.inject.Inject

class NavigateToFileListUseCase @Inject constructor(
    private val navigator: Navigator
) {
    operator fun invoke() {
        return navigator.navigateAndClearBackStack(Routes.FilesList(), Routes.OnBoarding, true)
    }
}