package com.dertefter.file_list.presentation.content

import com.dertefter.data.model.PrettyPath
import com.dertefter.file_list.presentation.Action
import java.io.File

sealed interface MenuState {
    data object Hide : MenuState
    data class Show(
        val path: String,
    ) : MenuState

}