package com.dertefter.wearfiles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.wear.compose.material3.AppScaffold
import com.dertefter.design.theme.TheTheme
import com.dertefter.onboarding.OnBoardingScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheTheme {
                AppScaffold(){
                    OnBoardingScreen()
                }

            }
        }
    }
}
