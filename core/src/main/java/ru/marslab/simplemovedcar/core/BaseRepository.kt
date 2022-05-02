package ru.marslab.simplemovedcar.core

import kotlinx.coroutines.CoroutineDispatcher

interface BaseRepository {
    val dispatcher: CoroutineDispatcher
}
