package com.example.mythos.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mythos.R
import com.example.mythos.ui.theme.MythosTheme

@Composable
fun SplashScreen(
    onLoginClick: () -> Unit
) {

    val buttonColor = Color(0xFF0E5B3D)
    val orangeColor = Color(0xFFFFA726)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF7F997F),
                        Color(0xFF4E6B2B)
                    )
                )
            )
            .padding(horizontal = 31.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Image(
            painter = painterResource(
                id = R.drawable.mythos_logo_vector
            ),
            contentDescription = "Logo do Mythos",
            modifier = Modifier.size(170.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(
            modifier = Modifier.height(25.dp)
        )


        Text(
            text = "WELCOME",
            color = Color.White,
            fontSize = 42.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(14.dp)
        )

        // FRASE
        Text(
            text = "Do meditation. Stay focused\nLive a healthy life",
            color = Color.White,
            fontSize = 22.sp,
            fontFamily = FontFamily.Serif,
            lineHeight = 32.sp,
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(135.dp)
        )


        Button(
            onClick = {
                onLoginClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            shape = RoundedCornerShape(19.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor
            )
        ) {

            Text(
                text = "Login With Email",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(
            modifier = Modifier.height(28.dp)
        )

        // SIGN UP
        Text(
            text = AnnotatedString.Builder().apply {

                append("Don't have an account? ")

                pushStyle(
                    SpanStyle(
                        color = orangeColor,
                        fontWeight = FontWeight.Bold
                    )
                )

                append("Sign Up")

                pop()

            }.toAnnotatedString(),

            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    MythosTheme {
        SplashScreen(
            onLoginClick = {}
        )
    }
}