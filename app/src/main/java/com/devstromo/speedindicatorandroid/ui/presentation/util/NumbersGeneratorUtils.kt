package com.devstromo.speedindicatorandroid.ui.presentation.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

fun generateNumbersEveryFiveSeconds(): Flow<Int> = flow {
    while (true) {
        emit(Random.nextInt(0, 241))
        delay(5000L)
    }
}