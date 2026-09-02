package com.example.mythos.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mythos.R
import com.example.mythos.ui.theme.MythosTheme

@Composable
fun LoginScreen(
    onBackClick: () -> Unit = {}
) {

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    val backgroundColor = Color(0xFF5F7D3F)
    val darkGreen = Color(0xFF0E5B3D)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 31.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
 Text(
            text = "<",
            color = Color.White,
            fontSize = 42.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 35.dp)
                .clickable {
                    onBackClick()
                }
        )

        Spacer(modifier = Modifier.height(15.dp))

        Image(
            painter = painterResource(id = R.drawable.mythos_logo_vector),
            contentDescription = "Logo do Mythos",
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Sign In",
            color = Color.White,
            fontSize = 38.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Welcome back! Please login to your account.",
            color = Color.White,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(45.dp))

        BasicTextField(
            value = email,
            onValueChange = { email = it },
            singleLine = true,
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 18.sp
            ),
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                Column {
                    if (email.isEmpty()) {
                        Text(
                            text = "Email",
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 18.sp
                        )
                    }

                    innerTextField()

                    Spacer(modifier = Modifier.height(8.dp))

                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                    ) {
                        drawLine(
                            color = Color.White,
                            start = androidx.compose.ui.geometry.Offset(0f, 0f),
                            end = androidx.compose.ui.geometry.Offset(size.width, 0f),
                            strokeWidth = 2f
                        )
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        BasicTextField(
            value = senha,
            onValueChange = { senha = it },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 18.sp
            ),
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                Column {
                    if (senha.isEmpty()) {
                        Text(
                            text = "Senha",
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 18.sp
                        )
                    }

                    innerTextField()

                    Spacer(modifier = Modifier.height(8.dp))

                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                    ) {
                        drawLine(
                            color = Color.White,
                            start = androidx.compose.ui.geometry.Offset(0f, 0f),
                            end = androidx.compose.ui.geometry.Offset(size.width, 0f),
                            strokeWidth = 2f
                        )
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Esqueceu a Senha?",
            color = Color.White,
            fontSize = 15.sp,
            modifier = Modifier.align(Alignment.End)
        )

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = darkGreen
            )
        ) {
            Text(
                text = "Login",
                color = Color.White,
                fontSize = 22.sp
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Don't have an account? Sign Up",
            color = Color.White,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MythosTheme {
        LoginScreen()
    }
}