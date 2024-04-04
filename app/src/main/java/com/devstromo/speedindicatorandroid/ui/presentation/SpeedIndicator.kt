package com.devstromo.speedindicatorandroid.ui.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.devstromo.speedindicatorandroid.ui.presentation.util.generateNumbersEveryFiveSeconds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SpeedIndicator() {
    var sliderValue by remember {
        mutableFloatStateOf(0f)
    }
    var speedValue by remember {
        mutableIntStateOf(0)
    }
    val coroutineScope = rememberCoroutineScope()
    val numberFlow = generateNumbersEveryFiveSeconds()
    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.Default) {
            numberFlow.collect { randomNumber ->
                speedValue = randomNumber
                println("Generated number 2: $randomNumber")
                println("Speed value: $speedValue")
            }
        }
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
                    this.rotationZ = mapValueToRange(
                        originalValue = sliderValue,
                        originalMin = 0f, originalMax = 240f,
                        newMin = 140f, newMax = 400f
                    )
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

fun mapValueToRange(
    originalValue: Float,
    originalMin: Float, originalMax: Float,
    newMin: Float, newMax: Float
): Float {
    return (originalValue - originalMin) / (originalMax - originalMin) * (newMax - newMin) + newMin
}