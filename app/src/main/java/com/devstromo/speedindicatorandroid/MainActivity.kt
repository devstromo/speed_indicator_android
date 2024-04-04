package com.devstromo.speedindicatorandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.devstromo.speedindicatorandroid.ui.presentation.SpeedIndicator
import com.devstromo.speedindicatorandroid.ui.theme.SpeedIndicatorAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpeedIndicatorAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SpeedIndicator()
                }
            }
        }
    }
}



@Preview(
    showBackground = true,
    device = "spec:parent=pixel_5,orientation=landscape"
)
@Composable
fun SpeedometerPreview() {
    SpeedIndicatorAndroidTheme {
        SpeedIndicator()
    }
}