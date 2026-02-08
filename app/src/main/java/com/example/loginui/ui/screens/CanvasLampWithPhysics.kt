package com.example.loginui.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun CanvasLampWithPhysics(
    glow: Float,
    onToggle: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val threadOffset = remember { Animatable(0f) }
    val maxPull = 70f

    Box(modifier = Modifier.size(120.dp)) {

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragEnd = {
                            if (threadOffset.value > maxPull * 0.75f) {
                                onToggle()
                            }

                            scope.launch {
                                threadOffset.animateTo(
                                    targetValue = 0f,
                                    animationSpec = spring(
                                        dampingRatio = Spring.DampingRatioMediumBouncy,
                                        stiffness = Spring.StiffnessLow
                                    )
                                )
                            }
                        }
                    ) { _, dragAmount ->
                        scope.launch {
                            threadOffset.snapTo(
                                (threadOffset.value + dragAmount.y)
                                    .coerceIn(0f, maxPull)
                            )
                        }
                    }
                }
        ) {

            val centerX = size.width / 2

            // Lamp Glow
            if (glow > 0f) {
                drawCircle(
                    color = Color.Green.copy(alpha = glow * 0.35f),
                    radius = size.width * (0.9f + glow),
                    center = Offset(centerX, size.height * 0.35f)
                )
            }

            // Lampshade
            drawPath(
                path = Path().apply {
                    moveTo(centerX - 60, 40f)
                    lineTo(centerX + 60, 40f)
                    lineTo(centerX + 40, 110f)
                    lineTo(centerX - 40, 110f)
                    close()
                },
                color = lerp(Color.DarkGray, Color(0xFFB2FF59), glow)
            )

            // Stand
            drawRect(
                color = Color.Gray,
                topLeft = Offset(centerX - 6, 110f),
                size = Size(12f, 80f)
            )

            // Base
            drawOval(
                color = Color.Gray,
                topLeft = Offset(centerX - 40, 190f),
                size = Size(80f, 20f)
            )

            // Thread
            drawLine(
                color = Color.White,
                start = Offset(centerX + 35, 110f),
                end = Offset(
                    centerX + 35,
                    170f + threadOffset.value
                ),
                strokeWidth = 3f
            )
        }
    }
}

@Preview(
    name = "Lamp ON",
    showBackground = true,
    backgroundColor = 0xFF0E1621
)
@Composable
fun CanvasLampOnPreview() {
    CanvasLampWithPhysics(
        glow = 1f,
        onToggle = {}
    )
}