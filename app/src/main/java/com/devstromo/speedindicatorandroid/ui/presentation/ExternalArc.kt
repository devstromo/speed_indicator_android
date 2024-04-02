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
import com.devstromo.speedindicatorandroid.config.END_ANGLE
import com.devstromo.speedindicatorandroid.config.START_ANGLE

@Composable
fun ExternalArc() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val arcRadius = size.height * .95f

        drawArc(
            color = Color.Red,
            startAngle = START_ANGLE, //start angle is always in clockwise direction
            sweepAngle = END_ANGLE, // angle formed between the start angle
            useCenter = false,
            size = Size(arcRadius, arcRadius),
            topLeft = Offset(
                (canvasWidth / 2) - (arcRadius / 2),
                canvasHeight / 2 - (arcRadius / 2)
            ),
            style = Stroke(width = 10f, cap = StrokeCap.Round)
        )

        drawSpeedometerTicks(
            this,
            canvasWidth,
            canvasHeight,
            startAngle = START_ANGLE,
            sweepAngle = END_ANGLE,
        )
    }


}
