package com.dertefter.file_list.usecase

import com.dertefter.file_list.presentation.Action
import javax.inject.Inject

class GetActionsUseCase @Inject constructor(
    private val checkIfNavigateUpAvailableUseCase: CheckIfNavigateUpAvailableUseCase,
    private val getMoreActionsUseCase: GetMoreActionsUseCase
) {
    operator fun invoke(path: String): List<Action> {
        val actions = mutableListOf<Action>()

        val moreActions = getMoreActionsUseCase(path)

        val canNavigateUp = checkIfNavigateUpAvailableUseCase(path)

        if (canNavigateUp){
            actions.add(Action.MOVE_BACK)
        }

        if (moreActions.isNotEmpty()){
            actions.add(Action.MORE)
        }


        return actions

    }
}