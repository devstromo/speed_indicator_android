package com.devstromo.speedindicatorandroid.ui.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun ExternalArc() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val arcRadius = size.height * .95f

        drawArc(
            color = Color.Red,
            startAngle = 140f, //start angle is always in clockwise direction
            sweepAngle = 260f, // angle formed between the start angle
            useCenter = false,
            size = Size(arcRadius, arcRadius),
            topLeft = Offset(
                (canvasWidth / 2) - (arcRadius / 2),
                canvasHeight / 2 - (arcRadius / 2)
            ),
            style = Stroke(width = 10f, cap = StrokeCap.Round)
        )
    }
}