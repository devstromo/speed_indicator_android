package com.devstromo.speedindicatorandroid.ui.presentation.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.random.Random

fun CoroutineScope.startNumberGenerator() {
    launch {
        while (isActive) {
            val randomNumber = Random.nextInt(0, 241)
            println("Generated number: $randomNumber")
            delay(5000L)
        }
    }
}