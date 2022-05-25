package ru.marslab.simplemovedcar.presentation.mainscreen.model

import androidx.compose.ui.unit.DpOffset
import ru.marslab.simplemovedcar.core.Action

sealed class MainScreenAction : Action {
    object CarStartClick : MainScreenAction()
    data class FieldClick(val position: DpOffset) : MainScreenAction()
}
