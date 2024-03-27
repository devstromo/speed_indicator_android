package com.devstromo.speedindicatorandroid.ui.presentation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun SpeedIndicator() {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        Greeting("Android")
    }
}