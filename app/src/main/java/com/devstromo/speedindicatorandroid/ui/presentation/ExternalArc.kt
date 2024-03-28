package com.devstromo.speedindicatorandroid.ui.presentation


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import kotlin.math.cos
import kotlin.math.sin

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

        drawSpeedometerTicks(
            this,
            canvasWidth,
            canvasHeight,
            arcRadius,
            startAngle = 140f,
            sweepAngle = 260f,
        )
    }


}

//fun drawSpeedometerTicks(
//    drawScope: DrawScope,
//    canvasWidth: Float,
//    canvasHeight: Float,
//    arcRadius: Float,
//    startAngle: Float,
//    sweepAngle: Float,
//) {
//    val paint = Paint().asFrameworkPaint().apply {
//        isAntiAlias = true
//        textSize = 40f
//        textAlign = android.graphics.Paint.Align.CENTER
//        color = android.graphics.Color.BLACK
//    }
//    val intervals = 12
//    val angleStep = sweepAngle / intervals
//
//    for (i in 0..intervals) {
//        val angle = startAngle + (i * angleStep)
//        val angleRad = Math.toRadians(angle.toDouble())
//        val cosAngle = cos(angleRad).toFloat()
//        val sinAngle = sin(angleRad).toFloat()
//
//        val innerTickRadius = arcRadius - 20f
//        val outerTickRadius = arcRadius
//
//        val startX = canvasWidth / 2 + innerTickRadius * cosAngle
//        val startY = canvasHeight / 2 + innerTickRadius * sinAngle
//        val endX = canvasWidth / 2 + outerTickRadius * cosAngle
//        val endY = canvasHeight / 2 + outerTickRadius * sinAngle
//
//        // Draw the tick mark
//        drawScope.drawLine(
//            Color.Blue,
//            start = Offset(startX, startY),
//            end = Offset(endX, endY),
//            strokeWidth = 5f
//        )
//
//        // Draw the number
//        val textRadius = innerTickRadius - 40f
//        val textX = canvasWidth / 2 + textRadius * cosAngle
//        val textY = canvasHeight / 2 + textRadius * sinAngle + paint.textSize / 2
//
//        drawScope.drawContext.canvas.nativeCanvas.drawText(
//            "${i * 20}",
//            textX,
//            textY,
//            paint
//        )
//    }
//}
