package com.devstromo.speedindicatorandroid.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Needle() {
    Box(
        modifier = Modifier
            .size(width = 100.dp, height = 50.dp)
            .background(color = Color.Blue, shape = RoundedCornerShape(10.dp))
    ) {
        // Content of the rectangle can be added here
    }
}