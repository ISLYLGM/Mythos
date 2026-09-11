package com.example.mythos.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.mythos.R

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Surface
import com.example.mythos.ui.theme.MythosTheme
@Composable
fun LoginScreen(
    onBackClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    onLoginSuccess: () -> Unit = {}
) {

    var email by remember {
        mutableStateOf("")
    }

    var senha by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val darkGreen = Color(0xFF006B45)
    val buttonGreen = Color(0xFF009F47)
    val lightBackground = Color(0xFFF7F7F5)
    val white = Color.White
    val textGray = Color(0xFF8A8A8A)


    Scaffold(
        containerColor = lightBackground
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(lightBackground)
        ) {


            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            ) {

                val largura = size.width
                val altura = size.height

                val path = Path().apply {

                    moveTo(0f, 0f)

                    lineTo(largura, 0f)

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


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),

                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                IconButton(
                    onClick = {
                        onBackClick()
                    },

                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 8.dp)
                ) {

                    Icon(
                        imageVector =
                            Icons.AutoMirrored.Filled.ArrowBack,

                        contentDescription =
                            "Voltar",

                        tint = white,

                        modifier = Modifier.size(30.dp)
                    )
                }


                Spacer(
                    modifier = Modifier.height(10.dp)
                )


                Image(
                    painter = painterResource(
                        id = R.drawable.mythos_logo_vector
                    ),

                    contentDescription =
                        "Logo do Mythos",

                    modifier = Modifier.size(105.dp)
                )


                Spacer(
                    modifier = Modifier.height(4.dp)
                )


                Text(
                    text = "Bem-vindo!",

                    color = white,

                    fontSize = 29.sp
                )


                Spacer(
                    modifier = Modifier.height(4.dp)
                )


                Text(
                    text = "Entre na sua conta",

                    color = white.copy(
                        alpha = 0.85f
                    ),

                    fontSize = 14.sp
                )


                Spacer(
                    modifier = Modifier.height(35.dp)
                )



                Card(
                    modifier = Modifier.fillMaxWidth(),

                    shape =
                        RoundedCornerShape(18.dp),

                    colors =
                        CardDefaults.cardColors(
                            containerColor = white
                        )
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 20.dp,
                                vertical = 20.dp
                            )
                    ) {

                        LoginTextField(
                            value = email,

                            onValueChange = {
                                email = it
                            },

                            placeholder = "E-mail",

                            icon = Icons.Default.Email
                        )


                        Spacer(
                            modifier =
                                Modifier.height(18.dp)
                        )


                        LoginTextField(
                            value = senha,

                            onValueChange = {
                                senha = it
                            },

                            placeholder = "Senha",

                            icon = Icons.Default.Lock,

                            password = true
                        )


                        Spacer(
                            modifier =
                                Modifier.height(7.dp)
                        )


                        Text(
                            text =
                                "Esqueceu a senha?",

                            color = buttonGreen,

                            fontSize = 13.sp,

                            modifier =
                                Modifier.align(
                                    Alignment.End
                                )
                        )


                        Spacer(
                            modifier =
                                Modifier.height(18.dp)
                        )


                        Button(
                            onClick = {

                                Toast.makeText(
                                    context,
                                    "Login realizado com sucesso!",
                                    Toast.LENGTH_SHORT
                                ).show()

                                onLoginSuccess()
                            },

                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),

                            shape =
                                RoundedCornerShape(28.dp),

                            colors =
                                ButtonDefaults.buttonColors(
                                    containerColor =
                                        buttonGreen
                                )
                        ) {

                            Text(
                                text = "Entrar",

                                color = white,

                                fontSize = 17.sp
                            )
                        }


                        Spacer(
                            modifier =
                                Modifier.height(12.dp)
                        )


                        /*
                         * ROW
                         */
                        Row(
                            modifier =
                                Modifier.fillMaxWidth(),

                            verticalAlignment =
                                Alignment.CenterVertically
                        ) {

                            Canvas(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(1.dp)
                            ) {

                                drawLine(
                                    color =
                                        Color(0xFFE5E5E5),

                                    start =
                                        Offset(0f, 0f),

                                    end =
                                        Offset(
                                            size.width,
                                            0f
                                        ),

                                    strokeWidth = 1f
                                )
                            }


                            Text(
                                text = "ou",

                                color = textGray,

                                fontSize = 13.sp,

                                modifier =
                                    Modifier.padding(
                                        horizontal = 10.dp
                                    )
                            )


                            Canvas(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(1.dp)
                            ) {

                                drawLine(
                                    color =
                                        Color(0xFFE5E5E5),

                                    start =
                                        Offset(0f, 0f),

                                    end =
                                        Offset(
                                            size.width,
                                            0f
                                        ),

                                    strokeWidth = 1f
                                )
                            }
                        }


                        Spacer(
                            modifier =
                                Modifier.height(12.dp)
                        )


                        OutlinedButton(
                            onClick = {

                                Toast.makeText(
                                    context,
                                    "Outra conta",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },

                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),

                            shape =
                                RoundedCornerShape(25.dp)
                        ) {

                            Text(
                                text =
                                    "Entrar com outra conta",

                                color = buttonGreen,

                                fontSize = 14.sp
                            )
                        }
                    }
                }


                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )


                Row(
                    horizontalArrangement =
                        Arrangement.Center,

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Text(
                        text =
                            "Ainda não possui uma conta? ",

                        color = textGray,

                        fontSize = 13.sp
                    )


                    Text(
                        text = "Cadastre-se",

                        color = buttonGreen,

                        fontSize = 13.sp,

                        modifier =
                            Modifier.clickable {
                                onSignUpClick()
                            }
                    )
                }


                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )


                InstagramButton(
                    onClick = {

                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(
                                "https://www.instagram.com/bia__e__bel/"
                            )
                        )

                        context.startActivity(intent)
                    },

                    buttonGreen = buttonGreen
                )
            }
        }
    }
}



@Composable
fun LoginTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    password: Boolean = false
) {

    OutlinedTextField(
        value = value,

        onValueChange = {
            onValueChange(it)
        },

        modifier = Modifier.fillMaxWidth(),

        singleLine = true,

        placeholder = {
            Text(
                text = placeholder
            )
        },

        leadingIcon = {

            Icon(
                imageVector = icon,

                contentDescription = null
            )
        },

        visualTransformation =
            if (password) {
                PasswordVisualTransformation()
            } else {
                androidx.compose.ui.text.input.VisualTransformation.None
            },

        shape =
            RoundedCornerShape(12.dp)
    )
}


@Composable
fun InstagramButton(
    onClick: () -> Unit,
    buttonGreen: Color
) {

    Column(
        modifier = Modifier.clickable {
            onClick()
        },

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        Canvas(
            modifier = Modifier.size(42.dp)
        ) {

            val tamanho =
                size.minDimension

            drawRoundRect(
                color = buttonGreen,

                size =
                    Size(
                        tamanho,
                        tamanho
                    ),

                cornerRadius =
                    CornerRadius(
                        tamanho * 0.25f,
                        tamanho * 0.25f
                    ),

                style =
                    Stroke(
                        width =
                            tamanho * 0.10f
                    )
            )


            drawCircle(
                color = buttonGreen,

                radius =
                    tamanho * 0.22f,

                center =
                    Offset(
                        tamanho / 2,
                        tamanho / 2
                    ),

                style =
                    Stroke(
                        width =
                            tamanho * 0.10f
                    )
            )


            drawCircle(
                color = buttonGreen,

                radius =
                    tamanho * 0.07f,

                center =
                    Offset(
                        tamanho * 0.76f,
                        tamanho * 0.24f
                    )
            )
        }


        Spacer(
            modifier =
                Modifier.height(6.dp)
        )


        Text(
            text = "@bia__e__bel",

            color = buttonGreen,

            fontSize = 15.sp
        )
    }
}
// ---------------------------------------------------------
// PREVIEW DA TELA DE LOGIN
// ---------------------------------------------------------

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    com.example.mythos.ui.theme.MythosTheme {
        androidx.compose.material3.Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            LoginScreen()
        }
    }
}