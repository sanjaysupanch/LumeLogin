package com.example.loginui.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginCard(glow: Float) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val glowColor = Color.Green.copy(alpha = glow * 0.6f)

    Card(
        modifier = Modifier
            .width(320.dp)
            .shadow(
                elevation = (glow * 18).dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = glowColor,
                spotColor = glowColor
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = lerp(
                Color(0xFF0F172A),
                Color(0xFF132F1B),
                glow
            )
        )
    ) {

        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "Welcome Back",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(Modifier.height(20.dp))

            OutlinedTextField(
                value = username,
                onValueChange = {username = it},
                label = { Text("Username") },
                textStyle = TextStyle(color = Color.White)
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                textStyle = TextStyle(color = Color.White)
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                )
            ) {
                Text("Login")
            }
        }
    }
}

@Preview(
    name = "Login Card OFF",
    showBackground = true,
    backgroundColor = 0xFF0E1621
)
@Composable
fun LoginCardOffPreview() {
    LoginCard(glow = 0f)
}

@Preview(
    name = "Login Card ON",
    showBackground = true,
    backgroundColor = 0xFF0E1621
)
@Composable
fun LoginCardOnPreview() {
    LoginCard(glow = 1f)
}