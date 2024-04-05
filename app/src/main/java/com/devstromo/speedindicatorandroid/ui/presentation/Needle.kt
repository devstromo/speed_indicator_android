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
            .size(width = 140.dp, height = 5.dp)
            .background(color = Color.Magenta.copy(alpha = .5f), shape = RoundedCornerShape(5.dp))
    )
}