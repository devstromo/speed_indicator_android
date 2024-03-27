package com.devstromo.speedindicatorandroid.ui.presentation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SpeedIndicator() {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        ExternalArc()
    }
}