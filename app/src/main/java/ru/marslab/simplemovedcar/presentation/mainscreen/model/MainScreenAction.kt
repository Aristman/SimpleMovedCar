package ru.marslab.simplemovedcar.presentation.mainscreen.model

import ru.marslab.simplemovedcar.core.Action

sealed class MainScreenAction : Action {
    object CarStartClick : MainScreenAction()
}
