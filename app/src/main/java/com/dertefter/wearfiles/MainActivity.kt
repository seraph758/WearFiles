package com.dertefter.wearfiles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material3.AppScaffold
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.dertefter.design.theme.TheTheme
import com.dertefter.navigation.Navigator
import com.dertefter.wearfiles.navigation.NavigationGraph
import com.dertefter.wearfiles.navigation.NavigationHandler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheTheme {

                AppScaffold {
                    val navController = rememberSwipeDismissableNavController()

                    NavigationHandler(
                        navigator = navigator,
                        navController = navController
                    )

                    NavigationGraph(navController = navController)
                }

            }
        }
    }
}
