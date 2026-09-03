package com.example.mythos.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.mythos.R


@Composable
fun SplashScreen(
    onLoginClick: () -> Unit
) {

    val darkGreen = Color(0xFF006B45)
    val buttonGreen = Color(0xFF009F47)
    val lightBackground = Color(0xFFF7F7F5)
    val white = Color.White
    val textGray = Color(0xFF8A8A8A)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightBackground)
    ) {

        /*
         * FUNDO VERDE COM DIAGONAL
         */
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(440.dp)
        ) {

            val largura = size.width
            val altura = size.height

            val path = Path().apply {

                moveTo(0f, 0f)

                lineTo(
                    largura,
                    0f
                )

                lineTo(
                    largura,
                    altura * 0.78f
                )

                lineTo(
                    0f,
                    altura
                )

                close()
            }

            drawPath(
                path = path,
                color = darkGreen
            )
        }


        /*
         * CONTEÚDO
         */
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally,

            verticalArrangement =
                Arrangement.Center
        ) {

            /*
             * LOGO
             */
            Image(
                painter = painterResource(
                    id = R.drawable.mythos_logo_vector
                ),

                contentDescription = "Logo do Mythos",

                modifier = Modifier.size(170.dp)
            )


            Spacer(
                modifier = Modifier.height(15.dp)
            )


            /*
             * NOME DO APP
             */
            Text(
                text = "MYTHOS",

                color = white,

                fontSize = 34.sp,

                fontWeight = FontWeight.Bold
            )


            Spacer(
                modifier = Modifier.height(8.dp)
            )


            /*
             * FRASE
             */
            Text(
                text = "Sabores, histórias e mitologias.",

                color = white.copy(alpha = 0.9f),

                fontSize = 16.sp,

                textAlign = TextAlign.Center
            )


            Spacer(
                modifier = Modifier.height(170.dp)
            )


            /*
             * TEXTO DE BOAS-VINDAS
             */
            Text(
                text = "Bem-vindo!",

                color = darkGreen,

                fontSize = 27.sp,

                fontWeight = FontWeight.Bold
            )


            Spacer(
                modifier = Modifier.height(8.dp)
            )


            Text(
                text = "Entre ou crie sua conta para continuar.",

                color = textGray,

                fontSize = 14.sp,

                textAlign = TextAlign.Center
            )


            Spacer(
                modifier = Modifier.height(25.dp)
            )


            /*
             * BOTÃO ENTRAR
             */
            Button(
                onClick = {
                    onLoginClick()
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),

                shape = RoundedCornerShape(28.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonGreen
                )
            ) {

                Text(
                    text = "Entrar",

                    color = white,

                    fontSize = 17.sp,

                    fontWeight = FontWeight.Bold
                )
            }


            Spacer(
                modifier = Modifier.height(12.dp)
            )


            Text(
                text = "Descubra o universo Mythos",

                color = textGray,

                fontSize = 13.sp,

                textAlign = TextAlign.Center
            )
        }
    }
}