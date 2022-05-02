package ru.marslab.simplemovedcar.presentation.mainscreen

import android.graphics.Point
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.marslab.simplemovedcar.core.BaseViewModel
import ru.marslab.simplemovedcar.presentation.mainscreen.model.MainScreenAction
import ru.marslab.simplemovedcar.presentation.mainscreen.model.MainScreenEvent
import ru.marslab.simplemovedcar.presentation.mainscreen.model.MainScreenState
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor() :
    BaseViewModel<MainScreenState, MainScreenEvent, MainScreenAction>(MainScreenState()) {
    override fun reduceStateByAction(
        currentState: MainScreenState,
        action: MainScreenAction
    ): MainScreenState =
        when (action) {
            MainScreenAction.CarStartClick -> {
                val carPosition = state.value.stopPosition
                state.value.copy(
                    startPosition = carPosition,
                    stopPosition = Point(
                        if (Random.nextBoolean()) 1 else 0,
                        if (Random.nextBoolean()) 1 else 0
                    ),
                    isMoved = true
                )
            }
        }
}
