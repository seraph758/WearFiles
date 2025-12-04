package com.dertefter.wearfiles.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.toRoute
import com.dertefter.file_list.FileListRoute
import com.dertefter.navigation.Routes
import com.dertefter.onboarding.OnBoardingRoute
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.nav.SwipeDismissableNavHost
import com.google.android.horologist.compose.nav.composable


@OptIn(ExperimentalHorologistApi::class)
@Composable
fun NavigationGraph(navController: NavHostController) {
    SwipeDismissableNavHost(
        navController = navController,
        startDestination = Routes.OnBoarding
    ) {

        composable<Routes.OnBoarding> {
            OnBoardingRoute()
        }

        composable<Routes.FilesList> { backStackEntry ->

            val args = backStackEntry.toRoute<Routes.FilesList>()

            FileListRoute(
                path = args.path
            )
        }

    }
}
