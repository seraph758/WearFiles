package com.dertefter.wearfiles.viewers

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.wear.compose.material3.AppScaffold
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.dertefter.design.theme.TheTheme
import com.dertefter.image_viewer.ImageViewerRoute
import com.dertefter.navigation.Navigator
import com.dertefter.navigation.Routes
import com.dertefter.text_viewer.TextViewerRoute
import com.dertefter.wearfiles.navigation.NavigationGraph
import com.dertefter.wearfiles.navigation.NavigationHandler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImageViewerActivity : ComponentActivity() {

    private var incomingFileUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.action == Intent.ACTION_VIEW && intent.data != null) {
            val uri = intent.data!!
            incomingFileUri = uri
        }


        setContent {
            TheTheme {
                AppScaffold {
                    ImageViewerRoute(incomingFileUri.toString())
                }

            }
        }
    }


}
