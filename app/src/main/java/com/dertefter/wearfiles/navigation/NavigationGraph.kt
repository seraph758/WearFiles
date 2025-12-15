package com.dertefter.wearfiles.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.toRoute
import com.dertefter.delete.DeleteRoute
import com.dertefter.file_list.FileListRoute
import com.dertefter.navigation.Routes
import com.dertefter.new_directory.NewDirectoryRoute
import com.dertefter.onboarding.OnBoardingRoute
import com.dertefter.rename.RenameRoute
import com.dertefter.text_viewer.TextViewerRoute
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.nav.SwipeDismissableNavHost
import com.google.android.horologist.compose.nav.composable


@OptIn(ExperimentalHorologistApi::class)
@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    SwipeDismissableNavHost(
        navController = navController, startDestination = Routes.OnBoarding
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


        composable<Routes.TextViewer> { backStackEntry ->

            val args = backStackEntry.toRoute<Routes.TextViewer>()

            TextViewerRoute(
                uriString = args.uriString
            )
        }


        composable<Routes.Rename> { backStackEntry ->

            val args = backStackEntry.toRoute<Routes.Rename>()

            RenameRoute(
                path = args.path
            )
        }

        composable<Routes.NewDirectory> { backStackEntry ->

            val args = backStackEntry.toRoute<Routes.NewDirectory>()

            NewDirectoryRoute(
                path = args.path
            )
        }

        composable<Routes.Delete> { backStackEntry ->

            val args = backStackEntry.toRoute<Routes.Delete>()

            DeleteRoute(
                path = args.path
            )
        }

    }
}
