package com.dertefter.wearfiles

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.dertefter.wearfiles.presentation.FilePickerScreen
import com.dertefter.wearfiles.presentation.NodeConnectionCard
import com.dertefter.wearfiles.presentation.NotificationPermissionRequest
import com.dertefter.wearfiles.ui.theme.WearFilesTheme
import com.google.android.gms.wearable.Wearable
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import kotlin.time.Duration.Companion.seconds

class MainActivity : ComponentActivity() {

    private var sharedUris by mutableStateOf<List<Uri>>(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent(intent)
        enableEdgeToEdge()
        setContent {
            WearFilesTheme {
                MonitorConnection()
                
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    topBar = {
                        NodeConnectionCard(
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .statusBarsPadding(),
                            status = TransferState.connectionStatus
                        )
                    }
                ) { contentPadding ->
                    NotificationPermissionRequest()
                    FilePickerScreen(
                        contentPadding = contentPadding,
                        initialUris = sharedUris
                    )
                }
            }
        }
    }

    @Composable
    private fun MonitorConnection() {
        val context = LocalContext.current
        val sender = remember { WearableFileSender(context) }
        
        LaunchedEffect(Unit) {
            while (true) {
                sender.checkConnection()
                delay(3.seconds)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_SEND -> {
                val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    intent.getParcelableExtra(Intent.EXTRA_STREAM)
                }
                sharedUris = listOfNotNull(uri)
            }
            Intent.ACTION_SEND_MULTIPLE -> {
                val uris = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM, Uri::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM)
                }
                sharedUris = uris ?: emptyList()
            }
        }
    }
}
