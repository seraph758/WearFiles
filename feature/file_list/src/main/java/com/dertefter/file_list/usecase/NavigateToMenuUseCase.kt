package com.dertefter.file_list.usecase

import android.util.Log
import com.dertefter.navigation.Navigator
import com.dertefter.navigation.Routes
import javax.inject.Inject

class NavigateToMenuUseCase @Inject constructor(
    private val navigator: Navigator
) {
    operator fun invoke(path: String) {
        Log.e("fvdiojfdvjodfv", "jkodfvioj")
        return navigator.navigate(Routes.Menu(path))
    }
}