package com.dertefter.wearfiles.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.dertefter.navigation.NavigationAction
import com.dertefter.navigation.Navigator

@Composable
fun NavigationHandler(
    navigator: Navigator,
    navController: NavHostController
) {
    LaunchedEffect(navigator) {
        navigator.navigationActions.collect { action ->
            when (action) {
                is NavigationAction.Navigate -> {
                    navController.navigate(action.route)
                }
                is NavigationAction.NavigateUp -> {
                    navController.navigateUp()
                }
                is NavigationAction.NavigateAndClearBackStack -> {
                    navController.navigate(action.route) {
                        popUpTo(action.popupTo) { inclusive = action.inclusive }
                        launchSingleTop = true
                    }
                }
            }
        }
    }
}