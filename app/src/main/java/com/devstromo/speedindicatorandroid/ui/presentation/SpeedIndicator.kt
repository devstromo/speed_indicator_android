package com.devstromo.speedindicatorandroid.ui.presentation

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devstromo.speedindicatorandroid.ui.presentation.util.generateNumbersEveryFiveSeconds
import com.devstromo.speedindicatorandroid.ui.theme.fontFamily
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SpeedIndicator() {
    var speedValue by remember {
        mutableIntStateOf(0)
    }
    val animatedSpeedValue by animateIntAsState(
        targetValue = speedValue,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearOutSlowInEasing
        ), label = ""
    )
    val coroutineScope = rememberCoroutineScope()
    val numberFlow = generateNumbersEveryFiveSeconds()
    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.Default) {
            numberFlow.collect { randomNumber ->
                speedValue = randomNumber
            }
        }
    }
    val configuration = LocalConfiguration.current

    val middleScreenWidth = (configuration.screenHeightDp * .40).dp
    val middleScreenHeight = (configuration.screenHeightDp * .40).dp
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {

        ExternalArc()
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = middleScreenWidth)
                .graphicsLayer {
                    this.transformOrigin = TransformOrigin(0f, 0f)
                    this.rotationZ = mapValueToRange(
                        originalValue = animatedSpeedValue.toFloat(),
                        originalMin = 0f, originalMax = 240f,
                        newMin = 140f, newMax = 400f
                    )
                }) {
            Needle()
        }
        AnimatedCounter(
            count = speedValue,
            modifier = Modifier
                .padding(top = middleScreenHeight)
                .align(Alignment.Center),
            style = TextStyle(
                fontFamily = fontFamily,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
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