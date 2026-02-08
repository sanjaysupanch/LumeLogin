package com.example.loginui.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LampLoginFinalScreen() {

    var isLampOn by remember { mutableStateOf(false) }

    val glow by animateFloatAsState(
        targetValue = if (isLampOn) 1f else 0f,
        animationSpec = tween(400),
        label = "glow"
    )

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0E1621))
            .padding(32.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        CanvasLampWithPhysics(
            glow = glow,
            onToggle = { isLampOn = !isLampOn }
        )

        AnimatedVisibility(
            visible = isLampOn,
            enter = fadeIn() + slideInHorizontally(),
            exit = fadeOut()
        ) {
            LoginCard(glow = glow)
        }
    }
}

@Preview(
    name = "Lamp Login Screen",
    showBackground = true,
    backgroundColor = 0xFF0E1621,
    widthDp = 800,
    heightDp = 400
)
@Composable
fun LampLoginFinalScreenPreview() {
    LampLoginFinalScreen()
}
