package ru.marslab.simplemovedcar.presentation.mainscreen.model

import android.graphics.Point

data class MainScreenState(
    val startPosition: Point = Point(0, 0),
    val stopPosition: Point = Point(0, 0),
    val isMoved: Boolean = false
)
