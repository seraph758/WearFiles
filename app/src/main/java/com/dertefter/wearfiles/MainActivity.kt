package com.dertefter.wearfiles

import android.content.ContentUris
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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

                    NavigationGraph(
                        navController = navController
                    )
                }

            }
        }
    }
}
