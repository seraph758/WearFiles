package com.dertefter.menu.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dertefter.menu.presentation.content.UiState
import com.dertefter.menu.usecase.GetMenuActionsUseCase
import com.dertefter.menu.usecase.NavigateBackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val navigateBackUseCase: NavigateBackUseCase,
    private val getMenuActionsUseCase: GetMenuActionsUseCase
) : ViewModel() {

    var state by mutableStateOf<UiState>(UiState.Loading)
        private set

    fun onEvent(event: Event) {
        when (event) {
            is Event.OnNavigateBack -> {
                navigateBackUseCase()
            }

            is Event.OnGetMenuActions -> {
                val actions = getMenuActionsUseCase(event.path)
                state = UiState.Success(
                    name = event.path.split("/").last(),
                    actions = actions
                )
            }

        }
    }


}
