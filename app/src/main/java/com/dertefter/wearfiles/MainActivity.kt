package com.dertefter.wearfiles

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.wear.compose.material3.AppScaffold
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.dertefter.design.theme.TheTheme
import com.dertefter.navigation.Navigator
import com.dertefter.navigation.Routes
import com.dertefter.wearfiles.navigation.NavigationGraph
import com.dertefter.wearfiles.navigation.NavigationHandler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    private var incomingFileUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.action == Intent.ACTION_VIEW && intent.data != null) {
            val uri = intent.data!!
            val mimeType = contentResolver.getType(uri)
            if (mimeType != null && mimeType.startsWith("text/")) {
                incomingFileUri = uri
            }
        }


        setContent {
            TheTheme {

                AppScaffold {
                    val navController = rememberSwipeDismissableNavController()

                    NavigationHandler(
                        navigator = navigator,
                        navController = navController
                    )

                    NavigationGraph(
                        navController = navController,
                        initialFileUri = incomingFileUri
                        )
                }

            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIncomingFile(intent)
    }

    private fun handleIncomingFile(intent: Intent) {
        if (intent.action == Intent.ACTION_VIEW) {
            val uri = intent.data ?: return

            val mimeType = contentResolver.getType(uri)
            if (mimeType != null && mimeType.startsWith("text/")) {
                val uri: Uri? = intent.data
                navigator.navigate(Routes.TextViewer(uri.toString()))

            }
        }
    }


}
