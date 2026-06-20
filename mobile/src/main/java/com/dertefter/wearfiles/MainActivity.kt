package com.dertefter.wearfiles

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dertefter.wearfiles.data.TransferRepository
import com.dertefter.wearfiles.presentation.FilePickerScreen
import com.dertefter.wearfiles.presentation.MainViewModel
import com.dertefter.wearfiles.presentation.NodeSelectionPager
import com.dertefter.wearfiles.presentation.NotificationPermissionRequest
import com.dertefter.wearfiles.ui.theme.WearFilesTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent(intent)
        enableEdgeToEdge()
        setContent {
            WearFilesTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    topBar = {
                        NodeSelectionPager(
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .statusBarsPadding(),
                            nodes = TransferRepository.availableNodes,
                            selectedNodeId = TransferRepository.selectedNodeId,
                            onNodeSelected = { viewModel.onNodeSelected(it) }
                        )
                    }
                ) { contentPadding ->
                    NotificationPermissionRequest()
                    FilePickerScreen(
                        contentPadding = contentPadding,
                        initialUris = viewModel.sharedUris
                    )
                }
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
                viewModel.updateSharedUris(listOfNotNull(uri))
            }
            Intent.ACTION_SEND_MULTIPLE -> {
                val uris = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM, Uri::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM)
                }
                viewModel.updateSharedUris(uris ?: emptyList())
            }
        }
    }
}
