package com.dertefter.wearfiles.common.viewers

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.wear.compose.material3.AppScaffold
import com.dertefter.design.theme.WearFilesTheme
import com.dertefter.image_viewer.ImageViewerRoute
import dagger.hilt.android.AndroidEntryPoint

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
            WearFilesTheme {
                AppScaffold {
                    ImageViewerRoute(incomingFileUri.toString())
                }

            }
        }
    }


}
