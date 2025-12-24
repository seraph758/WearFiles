package com.dertefter.wearfiles.presentation

import androidx.lifecycle.ViewModel
import com.dertefter.design.theme.ThemeManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    themeManager: ThemeManager
) : ViewModel() {

    val selectedColor = themeManager.themeColor

}
