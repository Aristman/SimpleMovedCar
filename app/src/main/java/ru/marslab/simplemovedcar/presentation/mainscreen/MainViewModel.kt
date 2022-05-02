package ru.marslab.simplemovedcar.presentation.mainscreen

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.marslab.simplemovedcar.core.BaseViewModel
import ru.marslab.simplemovedcar.presentation.mainscreen.model.MainScreenAction
import ru.marslab.simplemovedcar.presentation.mainscreen.model.MainScreenEvent
import ru.marslab.simplemovedcar.presentation.mainscreen.model.MainScreenState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() :
    BaseViewModel<MainScreenState, MainScreenEvent, MainScreenAction>(MainScreenState()) {
    override fun reduceStateByAction(
        currentState: MainScreenState,
        action: MainScreenAction
    ): MainScreenState {
        return currentState
    }
}
