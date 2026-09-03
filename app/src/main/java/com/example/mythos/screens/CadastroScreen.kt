package com.example.mythos.screens

import android.widget.Toast

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.mythos.R


@Composable
fun CadastroScreen(
    onBackClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onCadastroSuccess: () -> Unit = {}
) {

    var nome by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var senha by remember {
        mutableStateOf("")
    }

    var confirmarSenha by remember {
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
                    .verticalScroll(
                        rememberScrollState()
                    )
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

                        modifier =
                            Modifier.size(30.dp)
                    )
                }


                Spacer(
                    modifier =
                        Modifier.height(10.dp)
                )


                Image(
                    painter =
                        painterResource(
                            id =
                                R.drawable.mythos_logo_vector
                        ),

                    contentDescription =
                        "Logo do Mythos",

                    modifier =
                        Modifier.size(105.dp)
                )


                Spacer(
                    modifier =
                        Modifier.height(4.dp)
                )


                Text(
                    text = "Crie sua conta",

                    color = white,

                    fontSize = 29.sp
                )


                Spacer(
                    modifier =
                        Modifier.height(4.dp)
                )


                Text(
                    text =
                        "Cadastre-se para começar",

                    color =
                        white.copy(alpha = 0.85f),

                    fontSize = 14.sp
                )


                Spacer(
                    modifier =
                        Modifier.height(35.dp)
                )



                Card(
                    modifier =
                        Modifier.fillMaxWidth(),

                    shape =
                        RoundedCornerShape(18.dp),

                    colors =
                        CardDefaults.cardColors(
                            containerColor = white
                        )
                ) {

                    Column(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 20.dp,
                                    vertical = 20.dp
                                )
                    ) {

                        CadastroTextField(
                            value = nome,

                            onValueChange = {
                                nome = it
                            },

                            placeholder =
                                "Nome completo",

                            icon =
                                Icons.Default.Person
                        )


                        Spacer(
                            modifier =
                                Modifier.height(18.dp)
                        )


                        CadastroTextField(
                            value = email,

                            onValueChange = {
                                email = it
                            },

                            placeholder = "E-mail",

                            icon =
                                Icons.Default.Email,

                            keyboardType =
                                KeyboardType.Email
                        )


                        Spacer(
                            modifier =
                                Modifier.height(18.dp)
                        )


                        CadastroTextField(
                            value = senha,

                            onValueChange = {
                                senha = it
                            },

                            placeholder = "Senha",

                            icon =
                                Icons.Default.Lock,

                            password = true
                        )


                        Spacer(
                            modifier =
                                Modifier.height(18.dp)
                        )


                        CadastroTextField(
                            value =
                                confirmarSenha,

                            onValueChange = {
                                confirmarSenha = it
                            },

                            placeholder =
                                "Confirmar senha",

                            icon =
                                Icons.Default.Lock,

                            password = true
                        )


                        Spacer(
                            modifier =
                                Modifier.height(25.dp)
                        )



                        Button(
                            onClick = {

                                Toast.makeText(
                                    context,
                                    "Cadastro realizado!",
                                    Toast.LENGTH_SHORT
                                ).show()

                                onCadastroSuccess()
                            },

                            modifier =
                                Modifier
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
                                text = "Cadastrar",

                                color = white,

                                fontSize = 17.sp
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
                            "Já possui uma conta? ",

                        color = textGray,

                        fontSize = 13.sp
                    )


                    Text(
                        text = "Entrar",

                        color = buttonGreen,

                        fontSize = 13.sp,

                        modifier =
                            Modifier.padding(
                                start = 2.dp
                            )
                    )
                }
            }
        }
    }
}



@Composable
fun CadastroTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    password: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text
) {

    OutlinedTextField(
        value = value,

        onValueChange = {
            onValueChange(it)
        },

        modifier =
            Modifier.fillMaxWidth(),

        singleLine = true,

        keyboardOptions =
            KeyboardOptions(
                keyboardType = keyboardType
            ),

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
                VisualTransformation.None
            },

        shape =
            RoundedCornerShape(12.dp)
    )
}