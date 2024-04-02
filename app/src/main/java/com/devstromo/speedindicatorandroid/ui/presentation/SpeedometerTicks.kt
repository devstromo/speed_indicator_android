package com.devstromo.speedindicatorandroid.ui.presentation

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import kotlin.math.cos
import kotlin.math.sin

fun drawSpeedometerTicks(
    drawScope: DrawScope,
    canvasWidth: Float,
    canvasHeight: Float,
    startAngle: Float,
    sweepAngle: Float,
) {
    val paint = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        textSize = 40f
        textAlign = android.graphics.Paint.Align.CENTER
        color = android.graphics.Color.BLACK
    }
    val intervals = 12
    val angleStep = sweepAngle / intervals
    val simpleTickMultiplier = .1f
    val simpleTickStroke = 3f
    val middleTickMultiplier = 0.5f // To place the middle tick halfway between major ticks
    val middleStepTickWidth = 5f // Width for the bold middle tick
    val stepTickWidth = 8f // Width for the bold middle tick
    val radiusBase = canvasHeight * .43f
    val innerTickRadius = radiusBase + 40
    val outerTickRadius = radiusBase
    val middleTickRadius = radiusBase + 15 // Extend the middle tick a bit further
    val canvasMiddleWidth = canvasWidth * .5f
    val canvasMiddleHeight = canvasHeight * .5f

    for (i in 0..intervals) {
        val angle = startAngle + (i * angleStep)
        val angleRad = Math.toRadians(angle.toDouble())
        val cosAngle = cos(angleRad).toFloat()
        val sinAngle = sin(angleRad).toFloat()

        val startX = canvasMiddleWidth + innerTickRadius * cosAngle
        val startY = canvasMiddleHeight + innerTickRadius * sinAngle
        val endX = canvasMiddleWidth + outerTickRadius * cosAngle
        val endY = canvasMiddleHeight + outerTickRadius * sinAngle

        // Draw the tick mark
        drawScope.drawLine(
            Color.Black,
            start = Offset(startX, startY),
            end = Offset(endX, endY),
            strokeWidth = stepTickWidth
        )

        // Draw the number
        val textRadius = innerTickRadius - 85f
        val textX = canvasMiddleWidth + textRadius * cosAngle
        val textY = canvasMiddleHeight + textRadius * sinAngle + paint.textSize / 2

        drawScope.drawContext.canvas.nativeCanvas.drawText(
            "${i * 20}",
            textX,
            textY,
            paint
        )
        // Only draw middle ticks between main ticks, not after the last main tick
        if (i < intervals) {
            // Middle tick
            val middleAngle = angle + (angleStep * middleTickMultiplier)
            val middleAngleRad = Math.toRadians(middleAngle.toDouble())
            val middleCosAngle = cos(middleAngleRad).toFloat()
            val middleSinAngle = sin(middleAngleRad).toFloat()

            val middleStartX = canvasMiddleWidth + innerTickRadius * middleCosAngle
            val middleStartY = canvasMiddleHeight + innerTickRadius * middleSinAngle
            val middleEndX = canvasMiddleWidth + middleTickRadius * middleCosAngle
            val middleEndY = canvasMiddleHeight + middleTickRadius * middleSinAngle

            // Draw the middle
            drawScope.drawLine(
                Color.Black,
                start = Offset(middleStartX, middleStartY),
                end = Offset(middleEndX, middleEndY),
                strokeWidth = middleStepTickWidth
            )

            // draw simple lines
            for (j in 1..9) {
                val simpleAngle = angle + (angleStep * simpleTickMultiplier * j)
                val simpleAngleRad = Math.toRadians(simpleAngle.toDouble())
                val singleCosAngle = cos(simpleAngleRad).toFloat()
                val singleSinAngle = sin(simpleAngleRad).toFloat()
                val singleStartX = canvasMiddleWidth + innerTickRadius * singleCosAngle
                val singleStartY = canvasMiddleHeight + innerTickRadius * singleSinAngle
                val singleEndX = canvasMiddleWidth + middleTickRadius * singleCosAngle
                val singleEndY = canvasMiddleHeight + middleTickRadius * singleSinAngle

                // Draw the middle
                drawScope.drawLine(
                    Color.Black,
                    start = Offset(singleStartX, singleStartY),
                    end = Offset(singleEndX, singleEndY),
                    strokeWidth = simpleTickStroke
                )
            }
        }
    }
}
