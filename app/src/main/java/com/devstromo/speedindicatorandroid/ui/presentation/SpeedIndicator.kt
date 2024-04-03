package com.devstromo.speedindicatorandroid.ui.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun SpeedIndicator() {
    var sliderValue by remember {
        mutableFloatStateOf(0f)
    }
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        ExternalArc()
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = 100.dp)
                .graphicsLayer {
                    this.transformOrigin = TransformOrigin(0f, 0f)
                    this.rotationZ = sliderValue
                }) {
            Needle()
        }
        Text(
            text = sliderValue.toString(),
            modifier = Modifier.align(Alignment.Center)
        )
        Slider(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.BottomCenter),
            value = sliderValue,
            onValueChange = {
                sliderValue = it
            },
            valueRange = 0f..240f,
            enabled = true
        )
    }
}