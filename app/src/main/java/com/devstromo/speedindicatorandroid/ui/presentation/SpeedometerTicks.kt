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
    arcRadius: Float,
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
    val middleTickMultiplier = 0.5f // To place the middle tick halfway between major ticks
    val boldTickWidth = 8f // Width for the bold middle tick
    val radiusBase = drawScope.drawContext.size.height * .43f
    val innerTickRadius = radiusBase + 40
    val outerTickRadius = radiusBase
    val middleTickRadius = innerTickRadius + 20 // Extend the middle tick a bit further

    for (i in 0..intervals) {
        val angle = startAngle + (i * angleStep)
        val angleRad = Math.toRadians(angle.toDouble())
        val cosAngle = cos(angleRad).toFloat()
        val sinAngle = sin(angleRad).toFloat()

        val startX = canvasWidth / 2 + innerTickRadius * cosAngle
        val startY = canvasHeight / 2 + innerTickRadius * sinAngle
        val endX = canvasWidth / 2 + outerTickRadius * cosAngle
        val endY = canvasHeight / 2 + outerTickRadius * sinAngle

        // Draw the tick mark
        drawScope.drawLine(
            Color.Blue,
            start = Offset(startX, startY),
            end = Offset(endX, endY),
            strokeWidth = 5f
        )

        // Draw the number
        val textRadius = innerTickRadius - 80f
        val textX = canvasWidth / 2 + textRadius * cosAngle
        val textY = canvasHeight / 2 + textRadius * sinAngle + paint.textSize / 2

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

            val middleStartX = canvasWidth / 2 + outerTickRadius * middleCosAngle
            val middleStartY = canvasHeight / 2 + outerTickRadius * middleSinAngle
            val middleEndX = canvasWidth / 2 + middleTickRadius * middleCosAngle
            val middleEndY = canvasHeight / 2 + middleTickRadius * middleSinAngle

            // Draw the middle, bolder tick mark
            drawScope.drawLine(
                Color.Blue,
                start = Offset(middleStartX, middleStartY),
                end = Offset(middleEndX, middleEndY),
                strokeWidth = boldTickWidth
            )
        }
    }
}
