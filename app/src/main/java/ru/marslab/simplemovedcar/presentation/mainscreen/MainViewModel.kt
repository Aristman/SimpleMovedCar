package ru.marslab.simplemovedcar.presentation.mainscreen

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
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
    private var screenSize: DpOffset = DpOffset.Zero

    fun setScreenSize(width: Dp, height: Dp) {
        screenSize = DpOffset(width, height)
    }

    override fun reduceStateByAction(
        currentState: MainScreenState,
        action: MainScreenAction
    ): MainScreenState =
        when (action) {
            MainScreenAction.CarStartClick -> {
                val carPosition = state.value.stopPosition
                state.value.copy(
                    startPosition = carPosition,
                    stopPosition = DpOffset(
                        if (Random.nextBoolean()) screenSize.x else 0.dp,
                        if (Random.nextBoolean()) screenSize.y else 0.dp
                    )
                )
            }
            is MainScreenAction.FieldClick -> {
                val carPosition = state.value.stopPosition
                state.value.copy(
                    startPosition = carPosition,
                    stopPosition = action.position
                )
            }
        }
}
